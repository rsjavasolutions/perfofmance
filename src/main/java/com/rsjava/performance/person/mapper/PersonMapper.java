package com.rsjava.performance.person.mapper;

import com.rsjava.performance.car.mapper.CarMapper;
import com.rsjava.performance.person.model.PersonEntity;
import com.rsjava.performance.person.request.PersonRequest;
import com.rsjava.performance.person.response.PersonResponse;

import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonEntity mapToEntity(PersonRequest request) {
        return new PersonEntity(
                request.getName(),
                request.getSurname(),
                request.getBirthday()
        );
    }

    public static PersonResponse mapToResponseWithDetails(PersonEntity entity) {
        return PersonResponse.builder()
                .id(entity.getId())
                .uuid(entity.getUuid())
                .name(entity.getName())
                .surname(entity.getSurname())
                .birthday(entity.getBirthday())
                .creationDateTime(entity.getCreationDateTime())
                .cars(entity.getCars()
                        .stream()
                        .map(CarMapper::mapToResponse)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static PersonResponse mapToResponseWithoutDetails(PersonEntity entity) {
        return PersonResponse.builder()
                .id(entity.getId())
                .uuid(entity.getUuid())
                .name(entity.getName())
                .surname(entity.getSurname())
                .birthday(entity.getBirthday())
                .creationDateTime(entity.getCreationDateTime())
                .build();
    }
}
