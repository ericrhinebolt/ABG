package com.ericrhinebolt.abg.ABG.service;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.utils.pagination.Paged;
import com.ericrhinebolt.abg.ABG.utils.pagination.Paging;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService {

    private final GamesRepository gamesRepository;
    private final UserService userService;

    public GamesService(GamesRepository gamesRepository, UserService userService) {
        this.gamesRepository = gamesRepository;
        this.userService = userService;
    }

    public void addGames(Games games){
        gamesRepository.save(games);
    }

    public List<Games> findAll() {
        return gamesRepository.findAll();
    }

    public List<Games> searchGames(String search) {
        return gamesRepository.findGamesByTitle(search);
    }

    public Page<Games> searchGames(String search, Pageable pageable) {
        return gamesRepository.findGamesByTitlePage(search, pageable);
    }

    public Paged<Games> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "title");
        Page<Games> gamesPage = gamesRepository.findGameWithTitle(request);
        return new Paged<>(gamesPage, Paging.of(gamesPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Games> getPage(int pageNumber, int size, String search) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "title");
        Page<Games> gamesPage = searchGames(search, request);
        return new Paged<>(gamesPage, Paging.of(gamesPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Games> getPage(int pageNumber, int size, int userId) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC, "app_id");
        Page<Games> favoritesPage = userService.listFavorites(userId, request);
        return new Paged<>(favoritesPage, Paging.of(favoritesPage.getTotalPages(), pageNumber, size));
    }


}
