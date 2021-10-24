package com.rsjava.performance.person.service;

import com.rsjava.performance.car.CarRepository;
import com.rsjava.performance.car.model.CarEntity;
import com.rsjava.performance.person.PersonRepository;
import com.rsjava.performance.person.exception.PersonNotFoundException;
import com.rsjava.performance.person.mapper.PersonMapper;
import com.rsjava.performance.person.model.PersonEntity;
import com.rsjava.performance.person.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.rsjava.performance.person.mapper.PersonMapper.mapToResponseWithDetails;


@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    @Transactional
    public List<PersonResponse> getPeopleWithDetails() {

        return personRepository.findAllPeople()
                .stream()
                .map(PersonMapper::mapToResponseWithDetails)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PersonResponse> getPeopleWithoutDetails() {

        return personRepository.findAll()
                .stream()
                .map(PersonMapper::mapToResponseWithoutDetails)
                .collect(Collectors.toList());
    }

    @Transactional
    public PersonResponse getPerson(String uuid) {
        PersonEntity personEntity = personRepository.findByUuid(uuid).orElseThrow(() -> new PersonNotFoundException(uuid));

        return mapToResponseWithDetails(personEntity);
    }

    @Transactional
    public List<PersonResponse> getPeopleByCarBrand(String brand) {
        return personRepository.findAllPeopleByCarBrand(brand)
                .stream()
                .map(PersonMapper::mapToResponseWithDetails)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void saveData() {
        PersonEntity rambo = new PersonEntity("John", "Rambo", LocalDate.now());
        CarEntity mustang = new CarEntity("Ford", "Mustang", 2016, new BigDecimal("75000"));
        CarEntity challenger = new CarEntity("Dodge", "Challenger", 2016, new BigDecimal("78000"));
        rambo.addCar(mustang);
        rambo.addCar(challenger);

        PersonEntity rocky = new PersonEntity("Rocky", "Balboa", LocalDate.now());
        CarEntity audi = new CarEntity("Audi", "A8", 2019, new BigDecimal("175000"));
        CarEntity merc = new CarEntity("Mercedes", "S", 2009, new BigDecimal("68000"));
        rocky.addCar(audi);
        rocky.addCar(merc);

        PersonEntity budzian = new PersonEntity("Mariusz", "Budzian", LocalDate.now());
        CarEntity lambo = new CarEntity("Lamborgini", "Murcielago", 2008, new BigDecimal("700000"));
        CarEntity hummer = new CarEntity("Hummer", "H1", 2005, new BigDecimal("68000"));
        budzian.addCar(lambo);
        budzian.addCar(hummer);

        PersonEntity tyson = new PersonEntity("Mike", "Tyson", LocalDate.now());
        CarEntity viper = new CarEntity("Dode", "Viper", 2020, new BigDecimal("2000000"));
        CarEntity charger = new CarEntity("Dodge", "Charger", 2021, new BigDecimal("650000"));
        tyson.addCar(viper);
        tyson.addCar(charger);

        PersonEntity zdzisiek = new PersonEntity("Zdzisiek", "Nowak", LocalDate.now());

        CarEntity dacia = new CarEntity("Dacia", "Logan", 2020, new BigDecimal("10"));

        carRepository.save(dacia);

        personRepository.saveAll(Arrays.asList(rambo, rocky, budzian, tyson, zdzisiek));
    }
}
