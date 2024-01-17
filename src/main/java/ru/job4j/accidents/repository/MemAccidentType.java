package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class MemAccidentType implements AccidentTypeRepository {

    private Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    public MemAccidentType() {
        AccidentType accidentType1 = new AccidentType(1, "Две машины");
        AccidentType accidentType2 = new AccidentType(2, "Машина и человек");
        AccidentType accidentType3 = new AccidentType(3, "Машина и велосипед");
        accidentTypes.putIfAbsent(accidentType1.getId(), accidentType1);
        accidentTypes.putIfAbsent(accidentType2.getId(), accidentType2);
        accidentTypes.putIfAbsent(accidentType3.getId(), accidentType3);
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    @Override
    public Optional<AccidentType> findAccidentTypesById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }
}
