package ru.work.work_car_accident_V2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.work.work_car_accident_V2.model.AccidentType;
import ru.work.work_car_accident_V2.service.SimpleTypeService;

@Controller
@RequestMapping("/type")
public class TypeController {

    private final SimpleTypeService typeService;

    public TypeController(SimpleTypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/formIndexType")
    public String formIndexType(Model model) {
        var types = typeService.findAll();
        model.addAttribute("types", types);
        return "type/indexType";
    }

    @GetMapping("/formCreateType")
    public String formCreateType() {
        return "type/createType";
    }

    @GetMapping("/formUpdateType")
    public String formUpdateType(Model model, Integer id) {
        var rsl = typeService.findById(id);
        model.addAttribute("type", rsl.get());
        return "type/updateType";
    }

    @PostMapping("/createType")
    public String create(@ModelAttribute AccidentType accidentType) {
        typeService.save(accidentType);
        return "redirect:/type/formIndexType";
    }

    @PostMapping("/updateType")
    public String update(@ModelAttribute AccidentType accidentType) {
        typeService.update(accidentType);
        return "redirect:/type/formIndexType";
    }
}
