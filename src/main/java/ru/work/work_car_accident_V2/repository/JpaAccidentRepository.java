package ru.work.work_car_accident_V2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.work.work_car_accident_V2.model.Accident;

import java.util.List;

public interface JpaAccidentRepository extends CrudRepository<Accident, Integer> {

//    @Query(value = "select a from Accident a join fetch a.type join fetch a.rules")
    @Query(value = "select a from Accident a join fetch a.type")
    List<Accident> findAllAcc();
}
