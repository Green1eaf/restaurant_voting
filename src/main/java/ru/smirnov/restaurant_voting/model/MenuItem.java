package ru.smirnov.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "menu_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "actual_date", "name"}, name = "uk_menu_item")})
@Getter
@Setter
@NoArgsConstructor
public class MenuItem extends NamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "actual_date", nullable = false)
    @NotNull
    private LocalDate actualDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    public MenuItem(Integer id, String name, int price, @NotNull LocalDate actualDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.actualDate = actualDate;
        this.restaurant = restaurant;
    }
}
