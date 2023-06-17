package ru.smirnov.restaurant_voting.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smirnov.restaurant_voting.model.Vote;
import ru.smirnov.restaurant_voting.repository.VoteRepository;
import ru.smirnov.restaurant_voting.web.AuthUser;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteRepository repository;

    @GetMapping
    public List<Vote> getOwns() {
        int authId = AuthUser.authId();
        log.info("getOwns for userId={}", authId);
        return repository.getByUserId(authId);
    }

    @GetMapping("/by-date")
    public ResponseEntity<Vote> getOwnsByDate(@Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int authId = AuthUser.authId();
        if (date == null) date = LocalDate.now();
        log.info("getOwnsByDate for userId={} and {}", authId, date);
        return ResponseEntity.of(repository.getByUserIdAndDate(authId, date));
    }
}