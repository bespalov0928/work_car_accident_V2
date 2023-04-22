package ru.work.work_car_accident_V2.service;

import org.springframework.stereotype.Service;
import ru.work.work_car_accident_V2.model.User;
import ru.work.work_car_accident_V2.repository.JpaUserRepository;

import java.util.List;

@Service
public class SimpleUserService {
    private final JpaUserRepository userRepository;

    public SimpleUserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User person) {
        userRepository.save(person);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public List<User> findAll() {
        var rsl = (List<User>) userRepository.findAll();
        return rsl;
    }
}
