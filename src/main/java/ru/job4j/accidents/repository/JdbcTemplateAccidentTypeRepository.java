package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcTemplateAccidentTypeRepository implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return jdbc.query("SELECT id, name FROM accident_types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        AccidentType accidentTypeSql = jdbc.queryForObject("SELECT id, name FROM accident_types WHERE id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);
        return Optional.ofNullable(accidentTypeSql);
    }
}
