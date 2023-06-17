package ru.smirnov.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.model.MenuItem;
import ru.smirnov.restaurant_voting.model.Restaurant;
import ru.smirnov.restaurant_voting.to.RestaurantWithMenu;

import java.util.List;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantWithMenu withMenu(Restaurant restaurant) {
        List<DishRef> dishRefs = restaurant.getMenuItems().stream().map(MenuItem::getDishRef).toList();
        return new RestaurantWithMenu(restaurant.id(), restaurant.getName(), restaurant.getAddress(), dishRefs);
    }

    public static List<RestaurantWithMenu> withMenu(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantUtil::withMenu).toList();
    }
}