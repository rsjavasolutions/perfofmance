package com.rsjava.performance.person;

import com.rsjava.performance.person.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p " +
            "left join fetch p.cars")
    Set<PersonEntity> findAllPeople();

    @Query("SELECT p FROM PersonEntity p " +
            "left join fetch p.cars " +
            "WHERE p.uuid =:uuid")
    Optional<PersonEntity> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT p FROM PersonEntity p " +
            "left join fetch p.cars car " +
            "WHERE car.brand =:brand")
    Set<PersonEntity> findAllPeopleByCarBrand(@Param("brand") String brand);
}
