package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepository {
    Accident createAccident(Accident accident);

    List<Accident> findAllAccidents();
}
