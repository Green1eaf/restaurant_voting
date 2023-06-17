package ru.smirnov.restaurant_voting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "dish_ref", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uk_dish_ref")})
@Getter
@Setter
@NoArgsConstructor
public class DishRef extends NamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    public DishRef(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }
}
