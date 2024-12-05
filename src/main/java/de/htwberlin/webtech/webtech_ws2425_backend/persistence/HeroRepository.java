package de.htwberlin.webtech.webtech_ws2425_backend.persistence;

import de.htwberlin.webtech.webtech_ws2425_backend.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Integer> {
}
