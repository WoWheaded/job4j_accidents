package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class JdbcTemplateAccidentRepository implements AccidentRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Accident createAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO accidents (name, text, address, type_id) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        accident.setId((int) keys.get("id"));
        setRulesForAccident(accident);
        return accident;
    }

    private void setRulesForAccident(Accident accident) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
    }

    @Override
    public List<Accident> findAllAccidents() {
        return jdbc.query(
                "select distinct a.id, a.name, a.text, a.address, "
                        + "t.id AS type_id, t.name AS type_name, "
                        + "r.id AS rule_id, r.name AS rule_name from accidents a "
                        + "join accidents_rules ar ON a.id = ar.accident_id "
                        + "join rules r ON ar.rule_id = r.id "
                        + "join accident_types t ON a.type_id = t.id;",
                rs -> {
                    Map<Integer, Accident> accidentMap = new HashMap<>();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        Accident accident = accidentMap.get(id);
                        if (accident == null) {
                            accident = new Accident();
                            accident.setId(id);
                            accident.setName(rs.getString("name"));
                            accident.setText(rs.getString("text"));
                            accident.setAddress(rs.getString("address"));
                            accident.setType(new AccidentType(rs.getInt("type_id"), rs.getString("type_name")));
                            accident.setRules(new HashSet<>());
                            accidentMap.put(id, accident);
                        }
                        String ruleName = rs.getString("rule_name");
                        if (ruleName != null) {
                            Rule rule = new Rule();
                            rule.setId(rs.getInt("rule_id"));
                            rule.setName(ruleName);
                            accident.getRules().add(rule);
                        }
                    }
                    return accidentMap.values().stream().toList();
                });
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        String sql = "select distinct a.id, a.name, a.text, a.address, "
                + "t.id AS type_id, t.name AS type_name, "
                + "r.id AS rule_id, r.name AS rule_name from accidents a "
                + "join accidents_rules ar ON a.id = ar.accident_id "
                + "join rules r ON ar.rule_id = r.id "
                + "join accident_types t ON a.type_id = t.id "
                + "where a.id = ?";
        Accident accident = null;
        try (Connection connection = jdbc.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(new AccidentType(rs.getInt("type_id"), rs.getString("type_name")));
                    accident.setRules(new HashSet<>());
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("rule_name"));
                    accident.getRules().add(rule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(accident);
    }

    @Override
    public boolean updateAccident(Accident accident) {
        Optional<Accident> result = Optional.empty();
        String sql = "update accidents set name = ?, text = ?, address = ?, type_id = ? where id = ?";
        int updatedRows = jdbc.update(sql,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        if (updatedRows > 0) {
            jdbc.update("delete from accidents_rules where accident_id = ?", accident.getId());
            setRulesForAccident(accident);
            result = Optional.of(accident);
        }

        return result.isEmpty();
    }

    @Override
    public boolean deleteAccidentById(int id) {
        Optional<Accident> accident = findAccidentById(id);
        if (accident.isEmpty()) {
            return false;
        }
        jdbc.update("""
                        DELETE FROM accidents_rules
                        WHERE accident_id = ?;
                                        
                        DELETE FROM accidents
                        WHERE id = ?;
                                """,
                id, id);
        return true;
    }
}