package ru.smirnov.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.repository.DishRefRepository;
import ru.smirnov.restaurant_voting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class DishRefService {
    private final RestaurantRepository restaurantRepository;
    private final DishRefRepository dishRefRepository;

    @Transactional
    public DishRef save(int restaurantId, DishRef dishRef) {
        dishRef.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRefRepository.save(dishRef);
    }
}