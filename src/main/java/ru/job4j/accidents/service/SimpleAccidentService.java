package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.MemAccident;
import ru.job4j.accidents.repository.MemAccidentType;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final MemAccident memAccident;
    private final MemAccidentType memAccidentType;

    @Override
    public Accident createAccident(Accident accident) {
        accident.setType(memAccidentType.findAccidentTypesById(accident.getType().getId()).get());
        return memAccident.createAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return memAccident.findAllAccidents();
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return memAccident.findAccidentById(id);
    }

    @Override
    public boolean updateAccident(Accident accident) {
        accident.setType(memAccidentType.findAccidentTypesById(accident.getType().getId()).get());
        return memAccident.updateAccident(accident);
    }

    @Override
    public boolean deleteAccidentById(int id) {
        return memAccident.deleteAccidentById(id);
    }
}
