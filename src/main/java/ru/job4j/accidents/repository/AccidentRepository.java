package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {
    Accident createAccident(Accident accident);

    List<Accident> findAllAccidents();

    Optional<Accident> findAccidentById(int id);

    boolean updateAccident(Accident accident);

    boolean deleteAccidentById(int id);
}
