package ru.smirnov.restaurant_voting.web.restaurant;

import ru.smirnov.restaurant_voting.model.Restaurant;
import ru.smirnov.restaurant_voting.web.MatcherFactory;
import ru.smirnov.restaurant_voting.web.MatcherFactory.Matcher;

public class RestaurantTestData {
    public static final Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class);

    public static final int MAC_ID = 1;
    public static final int SHALYPIN_ID = 2;
    public static final int WASABI_ID = 3;

    public static final Restaurant mac = new Restaurant(MAC_ID, "Макдоналдс", "ул. Зеленая, 31");
    public static final Restaurant shalypin = new Restaurant(SHALYPIN_ID, "Шаляпин", "ул. Мира, 67");
    public static final Restaurant wasabi = new Restaurant(WASABI_ID, "Васаби", "ул. Бумажная, д.20");

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый", "не определен");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MAC_ID, "Мак", "ул. Урицкого, 33");
    }
}
