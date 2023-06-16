package ru.smirnov.restaurant_voting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.model.MenuItem;
import ru.smirnov.restaurant_voting.repository.MenuItemRepository;
import ru.smirnov.restaurant_voting.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class MenuItemService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public MenuItem save(int restaurantId, MenuItem menuItem) {
        menuItem.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return menuItemRepository.save(menuItem);
    }
}
