package com.ericrhinebolt.abg.ABG;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.models.Reviews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class GamesRepositoryTest {
    @Autowired
    private GamesRepository gamesRepository;

    @Test
    public void testFindGamesByTitle() {
        Games games = new Games();
        games.setTitle("Alchemist of War");
        List<Games> savedGames = gamesRepository.findGamesByTitle("Alchemist of War");
        assertThat(savedGames.get(0).getTitle()).isEqualTo(games.getTitle());
    }

    @Test
    public void testFindGamesByTitlePage() {
        Games games = new Games();
        games.setTitle("Alchemist of War");
        Pageable pageable = null;
        Page<Games> savedGames = gamesRepository.findGamesByTitlePage("Alchemist of War", pageable);
        List<Games> gameList = savedGames.toList();
        assertThat(gameList.get(0).getTitle()).isEqualTo(games.getTitle());
    }

    @Test
    public void testFindGameWithTitle() {
        Pageable pageable = null;
        Page<Games> games = gamesRepository.findGameWithTitle(pageable);
        List<Games> listGames = games.toList();
        assertThat(listGames.size()).isEqualTo(gamesRepository.findAll().size());
    }

    @Test
    public void testFindGameByAppId() {
        Games games = new Games();
        games.setAppId(1063730);
        games.setTitle("New World");
        Games savedGame = gamesRepository.findGameByAppId(1063730);
        assertEquals(savedGame.getTitle(), games.getTitle());
    }
}