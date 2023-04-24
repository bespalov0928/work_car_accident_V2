package ru.work.work_car_accident_V2.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.work.work_car_accident_V2.model.Role;
import ru.work.work_car_accident_V2.model.Status;
import ru.work.work_car_accident_V2.model.User;
import ru.work.work_car_accident_V2.repository.JpaUserRepository;
import ru.work.work_car_accident_V2.service.SimpleUserService;

@Controller
//@RequestMapping("/reg")
@AllArgsConstructor
public class RegController {
    private final PasswordEncoder encoder;
    private final SimpleUserService userService;

//    public RegController(PasswordEncoder encoder, SimpleUserService userService) {
//        this.encoder = encoder;
//        this.userService = userService;
//    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setStatus(Status.ACTIVE);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userService.save(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
