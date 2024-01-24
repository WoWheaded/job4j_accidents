package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.MemAccident;
import ru.job4j.accidents.repository.MemAccidentRule;
import ru.job4j.accidents.repository.MemAccidentType;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final MemAccident memAccident;
    private final MemAccidentType memAccidentType;
    private final MemAccidentRule memAccidentRule;

    @Override
    public Accident createAccident(Accident accident, List<Integer> ids) {
        setAccidentTypesAndRules(accident, ids);
        return memAccident.createAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return memAccident.findAllAccidents();
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return memAccident.findAccidentById(id);
    }

    @Override
    public void updateAccident(Accident accident, List<Integer> ids) {
        setAccidentTypesAndRules(accident, ids);
        memAccident.updateAccident(accident);
    }

    @Override
    public void deleteAccidentById(int id) {
        memAccident.deleteAccidentById(id);
    }

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(memAccidentType.findAccidentTypesById(typeId).get());
        accident.setRules(new HashSet<>(memAccidentRule.findRulesByIds(rulesIds)));
    }
}
