package ru.smirnov.restaurant_voting.mapper;

import org.mapstruct.MappingTarget;
import ru.smirnov.restaurant_voting.to.BaseTo;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<E, T extends BaseTo> {

    E toEntity(T to);

    List<E> toEntityList(Collection<T> tos);

    E updateFromTo(@MappingTarget E entity, T to);

    T toTo(E entity);

    List<T> toToList(Collection<E> entities);
}
