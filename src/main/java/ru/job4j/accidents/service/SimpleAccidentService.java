package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.MemAccident;

import java.util.List;

@Service
public class SimpleAccidentService implements AccidentService {

    private final MemAccident memAccident;

    @Autowired
    public SimpleAccidentService(MemAccident memAccident) {
        this.memAccident = memAccident;
    }

    @Override
    public Accident createAccident(Accident accident) {
        return memAccident.createAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return memAccident.findAllAccidents();
    }
}
