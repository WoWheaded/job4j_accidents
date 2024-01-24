package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.JdbcTemplateAccidentRepository;
import ru.job4j.accidents.repository.JdbcTemplateAccidentRuleRepository;
import ru.job4j.accidents.repository.JdbcTemplateAccidentTypeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JdbcTemplateAccident implements AccidentService {

    private final JdbcTemplateAccidentRepository accidentJdbcTemplate;
    private final JdbcTemplateAccidentTypeRepository accidentTypeJdbcTemplateRepository;
    private final JdbcTemplateAccidentRuleRepository accidentRuleJdbcTemplateRepository;

    @Override
    public Accident createAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return accidentJdbcTemplate.createAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return accidentJdbcTemplate.findAllAccidents();
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    @Override
    public void updateAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        accidentJdbcTemplate.updateAccident(accident);
    }

    @Override
    public void deleteAccidentById(int id) {
        accidentJdbcTemplate.deleteAccidentById(id);
    }

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(accidentTypeJdbcTemplateRepository.findAccidentTypesById(typeId).get());
        accident.setRules(new HashSet<>(accidentRuleJdbcTemplateRepository.findRulesByIds(rulesIds)));
    }
}