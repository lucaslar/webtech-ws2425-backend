package de.htwberlin.webtech.webtech_ws2425_backend.service;

import de.htwberlin.webtech.webtech_ws2425_backend.model.Hero;
import de.htwberlin.webtech.webtech_ws2425_backend.persistence.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HeroServiceTest {

    @Autowired
    private HeroService service;

    @MockBean
    private HeroRepository repository;

    @BeforeEach
    void setUpMockRepository() {
        final Hero yoda = new Hero(1, "Master Yoda", "Jedi", 1.0);
        final Hero hanSolo = new Hero(2, "Han Solo", "Rebellion", 1.85);
        doReturn(List.of(yoda, hanSolo)).when(repository).findAll();
        // doReturn(yoda).when(repository).findById(1L);
    }

    @Test
    void testGetHeroesByAffiliation() {
        final Iterable<Hero> resultAsIterable = service.getHeroes("rebellion");
        final List<Hero> result = StreamSupport.stream(resultAsIterable.spliterator(), false).toList();
        assertSame(1, result.size(), "Number of heroes should be one for affiliation=rebellion");
        assertEquals(result.getFirst().getName(), "Han Solo");
    }
}
