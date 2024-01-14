package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentTypeService {
    List<AccidentType> findAllAccidentTypes();

    Optional<AccidentType> findAccidentTypesById(int id);
}
