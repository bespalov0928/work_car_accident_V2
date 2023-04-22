package ru.work.work_car_accident_V2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.work.work_car_accident_V2.model.AccidentType;

import java.util.List;

public interface JpaTypeRepository extends CrudRepository<AccidentType, Integer> {

//    @Query(value = "select id, name from AccidentType")
//    List<AccidentType> findAllType();
}
