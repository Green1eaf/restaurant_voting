package ru.smirnov.restaurant_voting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.smirnov.restaurant_voting.model.User;
import ru.smirnov.restaurant_voting.to.UserTo;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTo> {

    @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
    @Mapping(target = "roles", expression = "java({Role.USER})")
    @Override
    User toEntity(UserTo to);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
    @Override
    User updateFromTo(@MappingTarget User entity, UserTo to);
}
