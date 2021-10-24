package com.rsjava.performance.car;

import com.rsjava.performance.car.response.CarResponse;
import com.rsjava.performance.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCar(@PathVariable String uuid) {
        return carService.getCar(uuid);
    }

    @GetMapping("models")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCarModels() {
        return carService.getCarModels();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    @GetMapping("people")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getCarsByPersonName(@RequestParam String personName) {
        return carService.getCarsByPersonName(personName);
    }
}