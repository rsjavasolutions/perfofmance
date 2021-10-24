package com.rsjava.performance.person.response;

import com.rsjava.performance.car.response.CarResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonResponse {
    @EqualsAndHashCode.Include
    private final String uuid;
    private final long id;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final LocalDateTime creationDateTime;
    private final Set<CarResponse> cars;
}
