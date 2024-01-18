package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.HibernateAccidentRuleRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateAccidentRuleService implements AccidentRuleService {

    private final HibernateAccidentRuleRepository hibernateAccidentRule;

    @Override
    public List<Rule> findAllAccidentRules() {
        return hibernateAccidentRule.findAllAccidentRules();
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return hibernateAccidentRule.findAccidentRuleById(rulesId);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return hibernateAccidentRule.findRulesByIds(ids);
    }
}