package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccidentRuleService {
    List<Rule> findAllAccidentRules();

    Optional<Rule> findAccidentRuleById(int rulesId);

    List<Rule> findRulesByIds(Collection<Integer> ids);
}