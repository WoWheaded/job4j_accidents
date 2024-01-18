package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentService {
    Accident createAccident(Accident accident, List<Integer> rulesIds);

    List<Accident> findAllAccidents();

    Optional<Accident> findAccidentById(int id);

    boolean updateAccident(Accident accident, List<Integer> rulesIds);

    boolean deleteAccidentById(int id);
}
