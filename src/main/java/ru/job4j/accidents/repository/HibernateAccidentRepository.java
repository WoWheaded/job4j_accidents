package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateAccidentRepository implements AccidentRepository {

    private final CrudRepository crudRepository;

    @Override
    public Accident createAccident(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public List<Accident> findAllAccidents() {
        return crudRepository.query("select distinct a from Accident a join fetch a.rules order by a.id", Accident.class);
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return crudRepository.optional("from Accident a join fetch a.rules where a.id = :fId", Accident.class,
                Map.of("fId", id));
    }

    @Override
    public boolean updateAccident(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
        return true;
    }

    @Override
    public boolean deleteAccidentById(int id) {
        return crudRepository.execute("DELETE FROM Accident WHERE id = :fId",
                Map.of("fId", id)) > 0;
    }
}