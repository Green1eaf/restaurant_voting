package ru.smirnov.restaurant_voting.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.smirnov.restaurant_voting.model.MenuItem;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem> {
}