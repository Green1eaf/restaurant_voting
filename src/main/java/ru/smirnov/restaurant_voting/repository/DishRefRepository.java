package ru.smirnov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.error.DataConflictException;
import ru.smirnov.restaurant_voting.model.DishRef;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRefRepository extends BaseRepository<DishRef> {
    @Query("SELECT d FROM DishRef d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<DishRef> get(int restaurantId, int id);

    @Query("SELECT d FROM DishRef d WHERE d.restaurant.id=:restaurantId ORDER BY d.name ASC")
    List<DishRef> getByRestaurant(int restaurantId);

    default DishRef checkExistOrBelong(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new DataConflictException("DishRef id=" + id + " doesn't exist or belong to Restaurant id=" + restaurantId));
    }
}