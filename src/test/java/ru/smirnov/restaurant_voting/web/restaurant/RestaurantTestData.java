package ru.smirnov.restaurant_voting.web.restaurant;

import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.model.MenuItem;
import ru.smirnov.restaurant_voting.model.Restaurant;
import ru.smirnov.restaurant_voting.to.RestaurantWithMenu;
import ru.smirnov.restaurant_voting.web.MatcherFactory;
import ru.smirnov.restaurant_voting.web.MatcherFactory.Matcher;

import java.time.LocalDate;
import java.util.List;

public class RestaurantTestData {
    public static final Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems");
    public static final Matcher<RestaurantWithMenu> RESTAURANT_MATCHER_WITH_MENU = MatcherFactory.usingIgnoringFieldsComparator(RestaurantWithMenu.class, "dishRefs.restaurant");

    public static final Matcher<DishRef> DISH_REF_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(DishRef.class, "restaurant");

    public static final int MAC_ID = 1;
    public static final int SHALYPIN_ID = 2;
    public static final int WASABI_ID = 3;

    public static final Restaurant mac = new Restaurant(MAC_ID, "Макдоналдс", "ул. Зеленая, 31");
    public static final Restaurant shalypin = new Restaurant(SHALYPIN_ID, "Шаляпин", "ул. Мира, 67");
    public static final Restaurant wasabi = new Restaurant(WASABI_ID, "Васаби", "ул. Бумажная, д.20");

    public static final DishRef mac_fof = new DishRef(1, "Филе-о-Фиш", 12700, mac);
    public static final DishRef mac_chb = new DishRef(2, "Чикенбургер", 5000, mac);
    public static final DishRef mac_chm20 = new DishRef(3, "Чикен Макнаггетс (20шт)", 27200, mac);

    public static final DishRef wasabi_rsh = new DishRef(7, "Ролл Сочная креветка", 25700, wasabi);
    public static final DishRef wasabi_rf = new DishRef(8, "Ролл Огонь", 31700, wasabi);
    public static final DishRef wasabi_rch = new DishRef(9, "Ролл Калифорния с цыпленком", 12900, wasabi);

    public static final MenuItem mac_1 = new MenuItem(1, LocalDate.now(), mac, mac_fof);
    public static final MenuItem mac_2 = new MenuItem(2, LocalDate.now(), mac, mac_chb);
    public static final MenuItem mac_3 = new MenuItem(3, LocalDate.now(), mac, mac_chm20);

    public static final MenuItem wasabi_7 = new MenuItem(7, LocalDate.now(), wasabi, wasabi_rsh);
    public static final MenuItem wasabi_8 = new MenuItem(8, LocalDate.now(), wasabi, wasabi_rf);
    public static final MenuItem wasabi_9 = new MenuItem(9, LocalDate.now(), wasabi, wasabi_rch);

    static {
//         set menu for today, sorted by name
        wasabi.setMenuItems(List.of(wasabi_9, wasabi_7));
        mac.setMenuItems(List.of(mac_1, mac_3, mac_2));
        shalypin.setEnabled(false);
        wasabi_rf.setEnabled(false);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый", "не определен");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MAC_ID, "Мак", "ул. Урицкого, 33");
    }

    public static DishRef getNewDish() {
        return new DishRef(null, "Новая Мак-еда", 12000, mac);
    }

    public static DishRef getUpdatedDish() {
        return new DishRef(mac_fof.id(), "Филе-о-Фиш-2", 13500, mac);
    }
}
