package ru.work.work_car_accident_V2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.work.work_car_accident_V2.model.Rule;

import java.util.List;

public interface  JpaRuleRepository extends CrudRepository<Rule, Integer> {
}
