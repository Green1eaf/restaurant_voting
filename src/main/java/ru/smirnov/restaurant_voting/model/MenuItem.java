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
@Getter
@Table(name = "menu_item", uniqueConstraints = {@UniqueConstraint(columnNames = {"actual_date", "dish_ref_id"}, name = "uk_menu_item")})
@Setter
@NoArgsConstructor
public class MenuItem extends BaseEntity {

    @Column(name = "actual_date", nullable = false)
    @NotNull
    private LocalDate actualDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_ref_id", insertable = false, updatable = false)
    @JsonIgnore
    // No cascade. Disable dishRef, already used in menu
    private DishRef dishRef;

    @Column(name = "dish_ref_id")
    private int dishRefId;

    public MenuItem(Integer id, @NotNull LocalDate actualDate, Restaurant restaurant, DishRef dishRef) {
        super(id);
        this.actualDate = actualDate;
        this.restaurant = restaurant;
        this.dishRef = dishRef;
        this.dishRefId = dishRef.id();
    }
}
