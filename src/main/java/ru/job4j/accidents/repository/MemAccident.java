package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class MemAccident implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger newId = new AtomicInteger(3);

    public MemAccident() {
        Accident accident1 = new Accident(1, "name1", "text1", "address1",
                new AccidentType(1, "Две машины"),
                Set.of(new Rule(1, "Статья. 1")));
        Accident accident2 = new Accident(2, "name2", "text2", "address2",
                new AccidentType(2, "Машина и человек"),
                Set.of(new Rule(2, "Статья. 2")));
        Accident accident3 = new Accident(3, "name3", "text3", "address3",
                new AccidentType(3, "Машина и велосипед"),
                Set.of(new Rule(3, "Статья. 3")));
        accidents.putIfAbsent(accident1.getId(), accident1);
        accidents.putIfAbsent(accident2.getId(), accident2);
        accidents.putIfAbsent(accident3.getId(), accident3);
    }

    @Override
    public Accident createAccident(Accident accident) {
        accident.setId(newId.incrementAndGet());
        accidents.putIfAbsent(accident.getId(), accident);
        return accident;
    }

    @Override
    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean updateAccident(Accident accident) {
        return (accidents.computeIfPresent(accident.getId(), (oldId, oldAccident) ->
                new Accident(oldAccident.getId(),
                        accident.getName(),
                        accident.getText(),
                        accident.getAddress(),
                        accident.getType(),
                        accident.getRules()))) != null;
    }

    @Override
    public boolean deleteAccidentById(int id) {
        return accidents.remove(id, accidents.get(id));
    }
}