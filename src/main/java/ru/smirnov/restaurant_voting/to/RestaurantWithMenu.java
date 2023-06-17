package ru.smirnov.restaurant_voting.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.smirnov.restaurant_voting.model.DishRef;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantWithMenu extends NamedTo {

    public RestaurantWithMenu(Integer id, String name, String address, List<DishRef> dishRefs) {
        super(id, name);
        this.address = address;
        this.dishRefs = dishRefs;
    }

    String address;
    List<DishRef> dishRefs;
}