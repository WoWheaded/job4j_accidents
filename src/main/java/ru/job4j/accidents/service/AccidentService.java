package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentService {
    Accident createAccident(Accident accident);

    List<Accident> findAllAccidents();
}
