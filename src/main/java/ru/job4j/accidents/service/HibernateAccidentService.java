package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.HibernateAccidentRepository;
import ru.job4j.accidents.repository.HibernateAccidentRuleRepository;
import ru.job4j.accidents.repository.HibernateAccidentTypeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateAccidentService implements AccidentService {

    private final HibernateAccidentRepository hibernateAccident;
    private final HibernateAccidentTypeRepository hibernateAccidentType;
    private final HibernateAccidentRuleRepository hibernateAccidentRule;

    @Override
    public Accident createAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return hibernateAccident.createAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return hibernateAccident.findAllAccidents();
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return hibernateAccident.findAccidentById(id);
    }

    @Override
    public void updateAccident(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        hibernateAccident.updateAccident(accident);
    }

    @Override
    public void deleteAccidentById(int id) {
        hibernateAccident.deleteAccidentById(id);
    }

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(hibernateAccidentType.findAccidentTypesById(typeId).get());
        accident.setRules(new HashSet<>(hibernateAccidentRule.findRulesByIds(rulesIds)));
    }
}