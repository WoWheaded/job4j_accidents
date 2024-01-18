package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateAccidentRuleRepository implements AccidentRuleRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Rule> findAllAccidentRules() {
        return crudRepository.query("from Rule", Rule.class);
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        return crudRepository.optional("from Rule where id = :fId", Rule.class,
                Map.of("fId", rulesId));
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return crudRepository.query("from Rule where id in (:fId)", Rule.class,
                Map.of("fId", ids));
    }
}