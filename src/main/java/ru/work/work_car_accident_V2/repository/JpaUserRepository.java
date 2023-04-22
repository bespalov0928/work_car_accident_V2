package ru.work.work_car_accident_V2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.work.work_car_accident_V2.model.User;

import java.util.Optional;

public interface JpaUserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
