package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.MemAccidentRule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleAccidentRuleService implements AccidentRuleService {

    private final MemAccidentRule memAccidentRule;

    @Override
    public List<Rule> findAllAccidentRules() {
        return memAccidentRule.findAllAccidentRules();
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return memAccidentRule.findAccidentRuleById(rulesId);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return ids.stream()
                .map(id -> findAccidentRuleById(id).get())
                .collect(Collectors.toList());
    }
}
