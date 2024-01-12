package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class MemAccident implements AccidentRepository {
    private Map<Integer, Accident> accidents;

    public MemAccident() {
        this.accidents = new ConcurrentHashMap<>();
        Accident accident1 = new Accident(1, "name1", "text1", "address1");
        Accident accident2 = new Accident(2, "name2", "text2", "address2");
        Accident accident3 = new Accident(3, "name3", "text3", "address3");
        accidents.putIfAbsent(accident1.getId(), accident1);
        accidents.putIfAbsent(accident2.getId(), accident2);
        accidents.putIfAbsent(accident3.getId(), accident3);
    }

    @Override
    public Accident createAccident(Accident accident) {
        Accident accidentNew = new Accident(accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress());
        accidents.putIfAbsent(accidentNew.getId(), accidentNew);
        return accidentNew;
    }

    @Override
    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }
}