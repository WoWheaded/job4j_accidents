package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.MemAccidentType;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final MemAccidentType memAccidentType;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return memAccidentType.findAllAccidentTypes();
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return memAccidentType.findAccidentTypesById(id);
    }
}
