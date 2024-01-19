package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

import java.util.List;

public interface DataAccidentRuleRepository extends CrudRepository<Rule, Integer> {
    List<Rule> findAllById(Iterable<Integer> ids);

    List<Rule> findAll();
}