package de.htwberlin.webtech.webtech_ws2425_backend.service;

import de.htwberlin.webtech.webtech_ws2425_backend.model.Hero;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeroService {

    private final Map<Integer, Hero> heroes = new HashMap<>(Map.of(
            1, new Hero(1, "Han Solo", "Rebellion", 1.85),
            2, new Hero(2, "Chewbacca", "Rebellion", 2.21),
            3, new Hero(3, "Darth Vader", "Empire", 2.03)
    ));

    private int nextId = 4;

    public List<Hero> getHeroes() {
        return this.heroes.values().stream().toList();
    }

    public Iterable<Hero> getHeroes(final String affiliation) {
        return this.getHeroes().stream()
                .filter(h -> h.getAffiliation() != null && h.getAffiliation().equalsIgnoreCase(affiliation))
                .collect(Collectors.toSet());
    }

    public Hero getHero(final int id) {
        return heroes.get(id);
    }

    public Hero addHero(final Hero hero) {
        hero.setId(nextId++);
        this.heroes.put(hero.getId(), hero);
        return hero;
    }

    public Hero editHero(final Hero hero) {
        if (!this.heroes.containsKey(hero.getId())) return null;

        heroes.put(hero.getId(), hero);
        return hero;
    }

    public boolean removeHero(final int id) {
        final boolean exists = this.heroes.containsKey(id);
        if (exists) this.heroes.remove(id);
        return exists;
    }
}
