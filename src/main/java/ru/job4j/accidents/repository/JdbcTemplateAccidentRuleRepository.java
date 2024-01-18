package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class JdbcTemplateAccidentRuleRepository implements AccidentRuleRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Rule> findAllAccidentRules() {
        return jdbc.query("SELECT id, name FROM rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Optional<Rule> findAccidentRuleById(int rulesId) {
        Rule rule = jdbc.queryForObject("select id, name from rules where id = ?",
                (rs, row) -> {
                    Rule findingRule = new Rule();
                    findingRule.setId(rs.getInt("id"));
                    findingRule.setName(rs.getString("name"));
                    return findingRule;
                }, rulesId);
        return Optional.ofNullable(rule);
    }

    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        String query = "select * from rules where id IN (" + ids.stream().map(Object::toString).
                collect(Collectors.joining(", ")) + ")";
        return new ArrayList<>(
                jdbc.query(query, (rs, row) -> new Rule(rs.getInt("id"), rs.getString("name")))
        );
    }
}
