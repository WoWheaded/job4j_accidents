package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.AuthorityRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAuthorityService implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        return Optional.ofNullable(authorityRepository.findByAuthority(authority));
    }
}
