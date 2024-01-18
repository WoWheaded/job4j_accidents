package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateAccidentTypeRepository implements AccidentTypeRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return crudRepository.optional("from AccidentType where id = :fId", AccidentType.class,
                Map.of("fId", id));
    }
}