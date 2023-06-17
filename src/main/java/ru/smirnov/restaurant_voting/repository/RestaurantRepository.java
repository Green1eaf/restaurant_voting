package ru.smirnov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.error.DataConflictException;
import ru.smirnov.restaurant_voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("""
            SELECT DISTINCT r from Restaurant r
            JOIN FETCH r.menuItems mi
            JOIN FETCH mi.dishRef d
            WHERE r.enabled=true AND mi.actualDate=:date AND d.enabled=true
            ORDER BY r.name ASC, d.name ASC
            """)
    List<Restaurant> getWithMenuByDate(LocalDate date);

    @Query("""
            SELECT r from Restaurant r
            JOIN FETCH r.menuItems mi
            JOIN FETCH mi.dishRef d
            WHERE r.id=:id AND r.enabled=true AND mi.actualDate=:date AND d.enabled=true
            ORDER BY d.name ASC
            """)
    Restaurant getWithMenuByRestaurantAndDate(int id, LocalDate date);

    @Query("SELECT r from Restaurant r WHERE r.enabled=true ORDER BY r.name ASC")
    List<Restaurant> getAllEnabled();

    default void checkAvailable(int id) {
        if (!getExisted(id).isEnabled()) {
            throw new DataConflictException("Restaurant " + id + " is unavailable");
        }
    }
}