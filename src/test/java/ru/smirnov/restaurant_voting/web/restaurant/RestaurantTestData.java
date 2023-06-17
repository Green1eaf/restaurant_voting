package ru.smirnov.restaurant_voting.web.restaurant;

import ru.smirnov.restaurant_voting.model.MenuItem;
import ru.smirnov.restaurant_voting.model.Restaurant;
import ru.smirnov.restaurant_voting.web.MatcherFactory;
import ru.smirnov.restaurant_voting.web.MatcherFactory.Matcher;

import java.time.LocalDate;
import java.util.List;

public class RestaurantTestData {
    public static final Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems");
    public static final Matcher<Restaurant> RESTAURANT_MATCHER_WITH_MENU = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems.restaurant");

    public static final int MAC_ID = 1;
    public static final int SHALYPIN_ID = 2;
    public static final int WASABI_ID = 3;

    public static final Restaurant mac = new Restaurant(MAC_ID, "Макдоналдс", "ул. Зеленая, 31");
    public static final Restaurant shalypin = new Restaurant(SHALYPIN_ID, "Шаляпин", "ул. Мира, 67");
    public static final Restaurant wasabi = new Restaurant(WASABI_ID, "Васаби", "ул. Бумажная, д.20");

    public static final MenuItem mac_1 = new MenuItem(1, "Филе-о-Фиш", 12700, LocalDate.now(), mac);
    public static final MenuItem mac_2 = new MenuItem(2, "Чикенбургер", 5000, LocalDate.now(), mac);
    public static final MenuItem mac_3 = new MenuItem(3, "Чикен Макнаггетс (20шт)", 27200, LocalDate.now(), mac);

    public static final MenuItem wasabi_7 = new MenuItem(7, "Ролл Сочная креветка", 25700, LocalDate.now(), wasabi);
    public static final MenuItem wasabi_8 = new MenuItem(8, "Ролл Огонь", 31700, LocalDate.now(), wasabi);
    public static final MenuItem wasabi_9 = new MenuItem(9, "Ролл Калифорния с цыпленком", 12900, LocalDate.now(), wasabi);

    static {
        // set menu for today, sorted by name
        wasabi.setMenuItems(List.of(wasabi_9, wasabi_8, wasabi_7));
        mac.setMenuItems(List.of(mac_1, mac_3, mac_2));
        shalypin.setEnabled(false);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый", "не определен");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MAC_ID, "Мак", "ул. Урицкого, 33");
    }
}
