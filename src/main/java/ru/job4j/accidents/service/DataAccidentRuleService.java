package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.DataAccidentRuleRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DataAccidentRuleService implements AccidentRuleService {

    private final DataAccidentRuleRepository dataAccidentRuleRepository;

    @Override
    public List<Rule> findAllAccidentRules() {
        return dataAccidentRuleRepository.findAll();
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return dataAccidentRuleRepository.findById(rulesId);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return dataAccidentRuleRepository.findAllById(ids);
    }
}