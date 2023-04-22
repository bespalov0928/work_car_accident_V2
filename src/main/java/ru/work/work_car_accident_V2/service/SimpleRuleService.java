package ru.work.work_car_accident_V2.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.repository.JpaRuleRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleRuleService {
    private final JpaRuleRepository ruleRepository;

    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    public Collection<Rule> findAll() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

    public Rule save(Rule rule) {
        ruleRepository.save(rule);
        return rule;
    }

    public Rule update(Rule rule) {
        var rsl = ruleRepository.findById(rule.getId());
        var rsl1 = rsl.get();
        rsl1.setName(rule.getName());
        ruleRepository.save(rsl1);
        return rsl1;
    }
}
