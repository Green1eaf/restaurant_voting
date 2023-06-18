package ru.smirnov.restaurant_voting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.smirnov.restaurant_voting.model.DishRef;
import ru.smirnov.restaurant_voting.model.MenuItem;
import ru.smirnov.restaurant_voting.model.Restaurant;
import ru.smirnov.restaurant_voting.to.RestaurantWithMenu;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantWithMenu> {

    @Mapping(target = "dishRefs", expression = "java(getDishRefs(restaurant))")
    @Override
    RestaurantWithMenu toTo(Restaurant restaurant);

    default List<DishRef> getDishRefs(Restaurant restaurant) {
        return restaurant.getMenuItems().stream()
                .map(MenuItem::getDishRef).toList();
    }
}
