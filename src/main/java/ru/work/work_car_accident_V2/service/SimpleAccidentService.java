package ru.work.work_car_accident_V2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.work.work_car_accident_V2.model.Accident;
import ru.work.work_car_accident_V2.model.AccidentType;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.repository.JpaAccidentRepository;
import ru.work.work_car_accident_V2.repository.JpaRuleRepository;
import ru.work.work_car_accident_V2.repository.JpaTypeRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SimpleAccidentService {

    private final JpaAccidentRepository accidentRepository;
    private final JpaTypeRepository typeRepository;
    private final JpaRuleRepository ruleRepository;

    public Optional<Accident> findById(Integer id) {
        Optional<Accident> rsl = accidentRepository.findById(id);
        return rsl;
    }

    public Accident save(Accident accident, String[] idRule) {
        Optional<AccidentType> type = typeRepository.findById(accident.getType().getId());
        List<Rule> listRule = getRules(idRule);

        accident.setType(type.get());
        accident.setRules(listRule);
        Accident rsl = accidentRepository.save(accident);
        return rsl;
    }

    public Accident update(Accident accident, String[] idRule) {
        Accident rsl = accidentRepository.save(accident);
        return rsl;
    }

    public Collection<Accident> findAll() {
        Collection<Accident> rsl = accidentRepository.findAllAcc();
        return rsl;
    }

    private List<Rule> getRules(String[] ids) {
        Integer[] arrInt = Stream.of(ids).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        Supplier<List<Rule>> suppler = LinkedList::new;
        BiConsumer<List<Rule>, Integer> biConsumer = new BiConsumer<List<Rule>, Integer>() {
            @Override
            public void accept(List<Rule> rules, Integer integer) {
                var ruleRsl = ruleRepository.findById(integer).get();
                rules.add(ruleRsl);
            }
        };
        BinaryOperator<List<Rule>> operator = (left, right) -> {
            left.addAll(right);
            return left;
        };
        var listRule = Stream.of(arrInt).collect(Collector.of(suppler, biConsumer, operator));
        return listRule;

    }

}
