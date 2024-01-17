package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class MemAccidentRule implements AccidentRuleRepository {

    private Map<Integer, Rule> accidentRules = new ConcurrentHashMap<>();

    public MemAccidentRule() {
        Rule rule1 = new Rule(1, "Статья. 1");
        Rule rule2 = new Rule(2, "Статья. 2");
        Rule rule3 = new Rule(3, "Статья. 3");
        accidentRules.putIfAbsent(rule1.getId(), rule1);
        accidentRules.putIfAbsent(rule2.getId(), rule2);
        accidentRules.putIfAbsent(rule3.getId(), rule3);
    }

    @Override
    public List<Rule> findAllAccidentRules() {
        return new ArrayList<>(accidentRules.values());
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return Optional.ofNullable(accidentRules.get(rulesId));
    }
}
