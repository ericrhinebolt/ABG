package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.service.GamesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class GamesServiceTest {

    @Autowired
    private GamesRepository gamesRepository;
    @Autowired
    private GamesService gamesService;

    @Test
    void testSearchGames() {
        String title = "Alchemist of War";
        assertEquals(gamesService.searchGames(title).get(0).getTitle(), gamesRepository.findGamesByTitle(title).get(0).getTitle());
    }

}