package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.HibernateAccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateAccidentTypeService implements AccidentTypeService {

    private final HibernateAccidentTypeRepository hibernateAccidentType;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return hibernateAccidentType.findAllAccidentTypes();
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return hibernateAccidentType.findAccidentTypesById(id);
    }
}