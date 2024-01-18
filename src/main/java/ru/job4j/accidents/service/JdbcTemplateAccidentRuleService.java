package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.JdbcTemplateAccidentRuleRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JdbcTemplateAccidentRuleService implements AccidentRuleService {

    private final JdbcTemplateAccidentRuleRepository accidentRuleJdbcTemplateRepository;

    @Override
    public List<Rule> findAllAccidentRules() {
        return accidentRuleJdbcTemplateRepository.findAllAccidentRules();
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return accidentRuleJdbcTemplateRepository.findAccidentRuleById(rulesId);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return accidentRuleJdbcTemplateRepository.findRulesByIds(ids);
    }
}