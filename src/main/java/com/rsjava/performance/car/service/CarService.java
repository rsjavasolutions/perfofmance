package com.rsjava.performance.car.service;

import com.rsjava.performance.car.CarRepository;
import com.rsjava.performance.car.exception.CarNotFoundException;
import com.rsjava.performance.car.mapper.CarMapper;
import com.rsjava.performance.car.model.CarEntity;
import com.rsjava.performance.car.model.CarModelOnly;
import com.rsjava.performance.car.response.CarResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.rsjava.performance.car.mapper.CarMapper.mapToResponse;


@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public List<CarResponse> getCars() {

        return carRepository.findAll()
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CarResponse> getCarsByPersonName(String personName) {

        return carRepository.findAllByPersonName(personName)
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<String> getCarModels() {
        return carRepository.findAllProjectedBy(CarModelOnly.class)
                .stream()
                .map(CarModelOnly::getModel)
                .collect(Collectors.toList());
    }


    @Transactional
    public CarResponse getCar(String uuid) {
        CarEntity carEntity = carRepository.findByUuid(uuid).orElseThrow(() -> new CarNotFoundException(uuid));
        return mapToResponse(carEntity);
    }
}
