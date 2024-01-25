package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.DataAccidentRepository;
import ru.job4j.accidents.repository.DataAccidentRuleRepository;
import ru.job4j.accidents.repository.DataAccidentTypeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DataAccidentService implements AccidentService {

    private final DataAccidentRepository dataAccidentRepository;
    private final DataAccidentTypeRepository dataAccidentTypeRepository;
    private final DataAccidentRuleRepository dataAccidentRuleRepository;

    @Override
    public Accident createAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return dataAccidentRepository.save(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return dataAccidentRepository.findAll();
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return dataAccidentRepository.findById(id);
    }

    @Override
    public boolean updateAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return dataAccidentRepository.save(accident) != null;
    }

    @Override
    public boolean deleteAccidentById(int id) {
        return dataAccidentRepository.deleteById(id);
    }

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(dataAccidentTypeRepository.findById(typeId).get());
        accident.setRules(new HashSet<>(dataAccidentRuleRepository.findAllById(rulesIds)));
    }
}