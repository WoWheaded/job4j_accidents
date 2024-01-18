package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.MemAccident;
import ru.job4j.accidents.repository.MemAccidentRule;
import ru.job4j.accidents.repository.MemAccidentType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final MemAccident memAccident;
    private final MemAccidentType memAccidentType;
    private final MemAccidentRule memAccidentRule;

    @Override
    public Accident createAccident(Accident accident) {
        accident.setType(memAccidentType.findAccidentTypesById(accident.getType().getId()).get());
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
    public boolean updateAccident(Accident accident, int[] ids) {
        accident.setType(memAccidentType.findAccidentTypesById(accident.getType().getId()).get());
        setRuleById(ids, accident);
        return memAccident.updateAccident(accident);
    }

    @Override
    public boolean deleteAccidentById(int id) {
        return memAccident.deleteAccidentById(id);
    }

    public void setRuleById(int[] rIds, Accident accident) {
        accident.setRules(Arrays.stream(rIds)
                .mapToObj(v -> memAccidentRule.findAccidentRuleById(v).get())
                .collect(Collectors.toSet()));
    }
}
