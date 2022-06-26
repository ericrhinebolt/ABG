package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class GamesRepositoryTest {
    @Autowired
    private GamesRepository gamesRepository;

    @Test
    public void testFindGamesByTitle() {
        Games games = new Games();

        games.setTitle("New World");

        List<Games> savedGames = gamesRepository.findGamesByTitle("New World");
        assertThat(savedGames.contains(games));

    }

    @Test
    public void testFindGamesByTitlePage() {
        Games games = new Games();

        games.setTitle("New World");

        Pageable pageable = null;
        Page<Games> savedGames = gamesRepository.findGamesByTitlePage("New World", pageable);
        List<Games> listGames = savedGames.getContent();
        assertThat(listGames.contains(games));
    }

    @Test
    public void testFindGameWithTitle() {
        Page<Games> games = null;
        Pageable pageable = null;
        games = gamesRepository.findGameWithTitle(pageable);
        List<Games> listGames = games.getContent();
        assertThat(listGames.contains(gamesRepository.findAll()));
    }

    @Test
    public void testFindGameByAppId() {
        Games games = new Games();
        games.setAppId(1063730);
        games.setTitle("New World");
        Games savedGame = gamesRepository.findGameByAppId(1063730);
        assertEquals(savedGame, games);
    }
}