package ru.work.work_car_accident_V2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.work.work_car_accident_V2.model.AccidentType;
import ru.work.work_car_accident_V2.repository.JpaTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTypeService {

    private final JpaTypeRepository jpaTypeRepository;

    public Optional<AccidentType> findById(Integer id) {
        Optional<AccidentType> accidentType = jpaTypeRepository.findById(id);
        return accidentType;
    }

    public Collection<AccidentType> findAll() {
        var rsl = (Collection<AccidentType>) jpaTypeRepository.findAll();
        return rsl;
    }

    public AccidentType save(AccidentType accidentType) {
        var rsl = jpaTypeRepository.save(accidentType);
        return rsl;
    }

    public AccidentType update(AccidentType accidentType) {
        var rsl = jpaTypeRepository.findById(accidentType.getId());
        var rsl1 = rsl.get();
        rsl1.setName(accidentType.getName());
        var rsl2 = jpaTypeRepository.save(rsl1);
        return rsl2;
    }


}
