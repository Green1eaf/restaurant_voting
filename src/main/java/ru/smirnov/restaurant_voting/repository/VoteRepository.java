package ru.smirnov.restaurant_voting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.restaurantId=:restaurantId")
    void deleteByRestaurantId(int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> getByUserId(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.actualDate=:date")
    Optional<Vote> getByUserIdAndDate(int userId, LocalDate date);
}