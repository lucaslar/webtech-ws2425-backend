package de.htwberlin.webtech.webtech_ws2425_backend.service;

import de.htwberlin.webtech.webtech_ws2425_backend.model.Hero;
import de.htwberlin.webtech.webtech_ws2425_backend.persistence.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    public Iterable<Hero> getHeroes() {
        return this.repository.findAll();
    }

    public Iterable<Hero> getHeroes(final String affiliation) {
        return StreamSupport.stream(this.getHeroes().spliterator(), false)
                .filter(h -> h.getAffiliation() != null && h.getAffiliation().equalsIgnoreCase(affiliation))
                .collect(Collectors.toSet());
    }

    public Optional<Hero> getHero(final int id) {
        return this.repository.findById(id);
    }

    public Hero addHero(final Hero hero) {
        return this.repository.save(hero);
    }

    public Hero editHero(final Hero hero) {
        if (!this.repository.existsById(hero.getId())) return null;

        return addHero(hero);
    }

    public boolean removeHero(final int id) {
        final boolean exists = this.repository.existsById(id); // this.heroes.containsKey(id);
        if (exists) this.repository.deleteById(id);
        return exists;
    }
}
