package ru.work.work_car_accident_V2.controller;

import lombok.AllArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.work.work_car_accident_V2.model.Accident;

import ru.work.work_car_accident_V2.model.User;
import ru.work.work_car_accident_V2.service.SimpleAccidentService;

import java.util.Collection;

@Controller
@AllArgsConstructor
public class IndexController {

    private final SimpleAccidentService service;

    @GetMapping("/index")
    public String index(Model model) {
        Collection<Accident> rsl = service.findAll();
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = ((org.springframework.security.core.userdetails.User) user).getUsername();
        model.addAttribute("user", user.equals("anonymousUser") ? user : username);
        model.addAttribute("accidents", rsl);
        return "index";
    }

}
