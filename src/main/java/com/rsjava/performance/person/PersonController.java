package com.rsjava.performance.person;

import com.rsjava.performance.person.response.PersonResponse;
import com.rsjava.performance.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse getPerson(@PathVariable String uuid) {
        return personService.getPerson(uuid);
    }

    @GetMapping("with-cars")
    public List<PersonResponse> getPeopleWithCars() {
        return personService.getPeopleWithDetails();
    }

    @GetMapping("without-cars")
    public List<PersonResponse> getPeopleWithoutCars() {
        return personService.getPeopleWithoutDetails();
    }

    @GetMapping("cars")
    public List<PersonResponse> getPeopleByCarBrand(@RequestParam (required = false) String brand) {
        return personService.getPeopleByCarBrand(brand);
    }
}
