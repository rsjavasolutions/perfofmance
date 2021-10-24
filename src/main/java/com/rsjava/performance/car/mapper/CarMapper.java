package com.rsjava.performance.car.mapper;

import com.rsjava.performance.car.model.CarEntity;
import com.rsjava.performance.car.request.CarRequest;
import com.rsjava.performance.car.response.CarResponse;

public class CarMapper {

    public static CarEntity mapToEntity(CarRequest request) {
        return new CarEntity(
                request.getBrand(),
                request.getModel(),
                request.getYear(),
                request.getPrice()
        );
    }

    public static CarResponse mapToResponse(CarEntity entity) {
        return new CarResponse(
                entity.getUuid(),
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getYear(),
                entity.getPrice(),
                entity.getCreationDateTime(),
                entity.getPerson().getUuid()
        );
    }
}
