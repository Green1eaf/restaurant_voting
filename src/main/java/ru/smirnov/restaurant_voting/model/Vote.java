package ru.smirnov.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "actual_date"}, name = "uk_vote")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @Column(name = "actual_date", nullable = false)
    @NotNull
    private LocalDate actualDate;

    @Column(name = "actual_time", nullable = false)
    @NotNull
    private LocalTime actualTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    // No cascade, disable for already voted restaurant
    private Restaurant restaurant;
}
