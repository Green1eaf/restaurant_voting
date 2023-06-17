package ru.smirnov.restaurant_voting.web.vote;

import ru.smirnov.restaurant_voting.model.Vote;
import ru.smirnov.restaurant_voting.web.MatcherFactory;
import ru.smirnov.restaurant_voting.web.MatcherFactory.Matcher;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.smirnov.restaurant_voting.web.restaurant.RestaurantTestData.*;
import static ru.smirnov.restaurant_voting.web.user.UserTestData.admin;
import static ru.smirnov.restaurant_voting.web.user.UserTestData.user;

public class VoteTestData {
    public static final LocalDate NOW = LocalDate.now(), NOW_MINUS_ONE = NOW.minusDays(1);
    public static final Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user");
    public static final Vote vote_1 = new Vote(1, user, NOW, LocalTime.parse("12:30:00"), MAC_ID);
    public static final Vote vote_2 = new Vote(2, user, NOW_MINUS_ONE, LocalTime.parse("09:15:00"), MAC_ID);
    public static final Vote vote_3 = new Vote(3, user, NOW.minusDays(2), LocalTime.parse("15:55:00"), WASABI_ID);
    public static final Vote vote_4 = new Vote(4, admin, NOW_MINUS_ONE, LocalTime.parse("08:15:00"), SHALYPIN_ID);
}
