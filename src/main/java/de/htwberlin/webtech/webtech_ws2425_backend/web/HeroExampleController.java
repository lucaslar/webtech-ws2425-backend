package de.htwberlin.webtech.webtech_ws2425_backend.web;

import de.htwberlin.webtech.webtech_ws2425_backend.model.Hero;
import de.htwberlin.webtech.webtech_ws2425_backend.service.HeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/heroes")
public class HeroExampleController {

    HeroService heroService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Hero>> getHeroes(@RequestParam final Optional<String> affiliation) {
        final Iterable<Hero> result = affiliation.isEmpty() || affiliation.get().isBlank()
                ? heroService.getHeroes()
                : heroService.getHeroes(affiliation.get());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable("id") final int id) {
        final Hero hero = heroService.getHero(id);
        if (hero == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(hero);
    }

    @PostMapping
    public ResponseEntity<Hero> addHero(@Valid @RequestBody final Hero hero) {
        final Hero created = heroService.addHero(hero);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") final int id, @RequestBody Hero body) {
        body.setId(id);
        final Hero updatedHero = heroService.editHero(body);
        return updatedHero == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedHero);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable("id") final int id) {
        return heroService.removeHero(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
