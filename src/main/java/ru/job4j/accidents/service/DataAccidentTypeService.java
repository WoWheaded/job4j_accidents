package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.DataAccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DataAccidentTypeService implements AccidentTypeService {

    private final DataAccidentTypeRepository dataAccidentTypeRepository;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return (List<AccidentType>) dataAccidentTypeRepository.findAll();
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return dataAccidentTypeRepository.findById(id);
    }
}