package com.rsjava.performance.car;

import com.rsjava.performance.car.model.CarEntity;
import com.rsjava.performance.person.model.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {

    @Query("SELECT c FROM CarEntity c " +
            "join fetch c.person")
    Set<CarEntity> findAll();

    List<CarEntity> findAllByBrand(String brand);

    <T> Collection<T> findAllProjectedBy(Class<T> type);

    Optional<CarEntity> findByUuid(String uuid);

    @Query("SELECT c FROM CarEntity c " +
            "join fetch c.person person " +
            "WHERE person.name =:name")
    Set<CarEntity> findAllByPersonName(@Param("name") String name);
}
