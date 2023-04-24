package ru.work.work_car_accident_V2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.service.SimpleRuleService;

@Controller
@RequestMapping("/rule")
public class RuleController {
    private final SimpleRuleService simpleRuleService;

    public RuleController(SimpleRuleService simpleRuleService) {
        this.simpleRuleService = simpleRuleService;
    }

    @GetMapping("/formIndexRule")
    public String formIndexRule(Model model) {
        var rules = simpleRuleService.findAll();
        model.addAttribute("rules", rules);
        return "rule/indexRule";
    }

    @GetMapping("/formCreateRule")
    public String formCreateRule() {
        return "rule/createRule";
    }

    @GetMapping("/formUpdateRule")
    public String formUpdateRule(Model model, @RequestParam("id") int id) {
        var rsl = simpleRuleService.findById(id);
        model.addAttribute("rule", rsl.get());
        return "rule/updateRule";
    }

    @PostMapping("/createRule")
    public String save(@ModelAttribute Rule rule) {
        simpleRuleService.save(rule);
        return "redirect:/rule/formIndexRule";
    }

    @PostMapping("updateRule")
    public String update(@ModelAttribute Rule rule) {
        simpleRuleService.update(rule);
        return "redirect:/rule/formIndexRule";
    }

}
