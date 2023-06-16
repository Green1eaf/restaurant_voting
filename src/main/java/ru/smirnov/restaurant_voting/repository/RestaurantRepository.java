package ru.smirnov.restaurant_voting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
}