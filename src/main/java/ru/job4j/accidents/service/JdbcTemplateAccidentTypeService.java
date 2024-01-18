package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.JdbcTemplateAccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JdbcTemplateAccidentTypeService implements AccidentTypeService {

    private final JdbcTemplateAccidentTypeRepository accidentJdbcTemplateRepository;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return accidentJdbcTemplateRepository.findAllAccidentTypes();
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return accidentJdbcTemplateRepository.findAccidentTypesById(id);
    }
}