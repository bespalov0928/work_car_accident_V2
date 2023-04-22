package ru.work.work_car_accident_V2.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.work.work_car_accident_V2.model.Accident;
import ru.work.work_car_accident_V2.model.AccidentType;
import ru.work.work_car_accident_V2.model.Photo;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.service.SimpleAccidentService;
import ru.work.work_car_accident_V2.service.SimplePhotoService;
import ru.work.work_car_accident_V2.service.SimpleRuleService;
import ru.work.work_car_accident_V2.service.SimpleTypeService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/acc")
public class AccController {

    private final SimpleAccidentService accidentService;
    private final SimpleTypeService typeService;
    private final SimpleRuleService ruleService;
    private final SimplePhotoService photoService;

     @GetMapping("/formCreateAcc")
    public String formCreateAcc(Model model) {
        Collection<AccidentType> types = typeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "/acc/createAcc";
    }

    @GetMapping("/formUpdateAcc")
    public String formUpdateAcc(@RequestParam("id") int id, Model model) {
        List<AccidentType> typeList = (List<AccidentType>) typeService.findAll();
        List<Rule> ruleList = (List<Rule>) ruleService.findAll();
        Optional<Accident> accident = accidentService.findById(id);
        List<Rule> rules = accident.get().getRules();
        Optional<AccidentType> accidentType = Optional.ofNullable(accident.get().getType());
        List<Photo> photos = photoService.findPhotosByAccident_Id(accident.get().getId());

        model.addAttribute("accident", accident.get());
        model.addAttribute("types", typeList);
        model.addAttribute("typeId", accidentType.isEmpty() ? 0 : accidentType.get().getId());
        model.addAttribute("rules", ruleList);
        model.addAttribute("rulesId", rules == null ? new ArrayList<>() : rules);
        model.addAttribute("photos", photos == null ? new ArrayList<>() : photos);
        return "/acc/updateAcc";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       HttpServletRequest req) {
        var idRule = req.getParameterValues("idRule");
        Accident rsl = accidentService.save(accident, idRule);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         HttpServletRequest req) {
        String[] idRule = req.getParameterValues("idRule");
        Accident rsl = accidentService.update(accident, idRule);
        return "redirect:/index";
    }
}
