package ru.smirnov.restaurant_voting.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.smirnov.restaurant_voting.error.DataConflictException;
import ru.smirnov.restaurant_voting.model.User;
import ru.smirnov.restaurant_voting.model.Vote;
import ru.smirnov.restaurant_voting.repository.RestaurantRepository;
import ru.smirnov.restaurant_voting.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository repository;
    private final RestaurantRepository restaurantRepository;

    @Setter
    static LocalTime deadline = LocalTime.of(11, 0);

    @Transactional
    public Vote createToday(User user, int restaurantId) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Vote> dbVote = repository.getByUserIdAndDate(user.id(), now.toLocalDate());
        dbVote.ifPresent(v -> {
            throw new DataConflictException("Already has been voted today");
        });
        restaurantRepository.checkAvailable(restaurantId);
        return repository.save(new Vote(null, user, now.toLocalDate(), now.toLocalTime(), restaurantId));
    }

    @Transactional
    public void updateToday(User user, int restaurantId, boolean deleteVote) {
        LocalDateTime now = LocalDateTime.now();
        if (now.toLocalTime().isAfter(deadline)) {
            throw new DataConflictException("Deadline for changing vote has been passed");
        }
        Optional<Vote> dbVote = repository.getByUserIdAndDate(user.id(), now.toLocalDate());
        Vote vote = dbVote.orElseThrow(() -> new DataConflictException("You does not voted today"));
        if (deleteVote) {
            repository.delete(vote.id());
        } else {
            restaurantRepository.checkAvailable(restaurantId);
            vote.setActualTime(now.toLocalTime());
            vote.setRestaurantId(restaurantId);
        }
    }
}
