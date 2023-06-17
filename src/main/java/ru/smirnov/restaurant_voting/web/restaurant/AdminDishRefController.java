package ru.smirnov.restaurant_voting.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.repository.DishRefRepository;
import ru.smirnov.restaurant_voting.service.DishRefService;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.smirnov.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static ru.smirnov.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishRefController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminDishRefController {

    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dish-ref";

    private final DishRefRepository repository;
    private final DishRefService service;

    @GetMapping("/{id}")
    public ResponseEntity<DishRef> get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get for restaurantId={}, id={}", restaurantId, id);
        return ResponseEntity.of(repository.get(restaurantId, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete for restaurantId={}, id={}", restaurantId, id);
        DishRef dishRef = repository.checkExistOrBelong(restaurantId, id);
        repository.delete(dishRef);
    }

    @GetMapping
    public List<DishRef> getByRestaurant(@PathVariable int restaurantId) {
        log.info("getByRestaurant for restaurantId={}", restaurantId);
        return repository.getByRestaurant(restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishRef> createWithLocation(@PathVariable int restaurantId, @Valid @RequestBody DishRef dishRef) {
        log.info("create {} for restaurantId={}", dishRef, restaurantId);
        checkNew(dishRef);
        DishRef created = service.save(restaurantId, dishRef);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int restaurantId, @PathVariable int id, @Valid @RequestBody DishRef dishRef) {
        log.info("update {} for restaurantId={}, id={}", dishRef, restaurantId, id);
        assureIdConsistent(dishRef, id);
        repository.checkExistOrBelong(restaurantId, id);
        service.save(restaurantId, dishRef);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void enable(@PathVariable int restaurantId, @PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        DishRef dishRef = repository.checkExistOrBelong(restaurantId, id);
        dishRef.setEnabled(enabled);
    }
}