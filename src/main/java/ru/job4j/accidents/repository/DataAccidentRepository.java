package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.function.BinaryOperator;

public interface DataAccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findAll();

    boolean save(Accident accident, List<Integer> rulesIds);

    boolean deleteById(int id);
}