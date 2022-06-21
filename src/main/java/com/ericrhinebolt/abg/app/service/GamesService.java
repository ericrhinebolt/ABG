package com.ericrhinebolt.abg.app.service;

import com.ericrhinebolt.abg.app.data.GamesRepository;
import com.ericrhinebolt.abg.app.data.UserRepository;
import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.utils.pagination.Paged;
import com.ericrhinebolt.abg.app.utils.pagination.Paging;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GamesService {

    private final GamesRepository gamesRepository;
    private final UserRepository userRepository;

    public GamesService(GamesRepository gamesRepository, UserRepository userRepository) {
        this.gamesRepository = gamesRepository;
        this.userRepository = userRepository;
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

    public Page<Games> listFavorites(int userId, Pageable pageable) {
        Set<Integer> favorites = userRepository.findFavoritesByUserId(userId);
        List<Games> list = new ArrayList<>();
        for (Integer i : favorites){
            Games game = gamesRepository.findGameByAppId(i);
            list.add(game);
        }
        return new PageImpl<>(list);
    }

//    Page<Games> convertSetToPage(Set<Integer> setFavorites, Pageable pageable) {
//        return null;
//    }
//
//    public Page<Games> setFavorites(int userId) {
//        Set<Integer> favorites = userRepository.findFavoritesByUserId(userId);
//    }

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
        Page<Games> favoritesPage = listFavorites(userId, request);
//        Page<Games> favorites = (Page<Games>) favoritesPage;
        return new Paged<>(favoritesPage, Paging.of(favoritesPage.getTotalPages(), pageNumber, size));
    }


}
