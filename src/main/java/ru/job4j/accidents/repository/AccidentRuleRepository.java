package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccidentRuleRepository {
    List<Rule> findAllAccidentRules();

    Optional<Rule> findAccidentRuleById(int rulesId);

    List<Rule> findRulesByIds(Collection<Integer> ids);
}