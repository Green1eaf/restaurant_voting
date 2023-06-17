package ru.smirnov.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;
import ru.smirnov.restaurant_voting.util.validation.NoHtml;

import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "uk_restaurant")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "address")
    @Size(max = 1024)
    @NoHtml
    @Nullable
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @JsonIgnore
    @ToString.Exclude
    @JsonInclude(JsonInclude.Include.NON_EMPTY) //https://stackoverflow.com/a/27964775/548473
    private List<MenuItem> menuItems;

    public Restaurant(Integer id, String name, @Nullable String address) {
        super(id, name);
        this.address = address;
    }
}