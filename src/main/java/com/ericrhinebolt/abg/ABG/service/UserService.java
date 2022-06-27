package com.ericrhinebolt.abg.ABG.service;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private UserRepository userRepository;

//    Method to return page implementation of list of favorites by user id
    public Page<Games> listFavorites(int userId, Pageable pageable) {
        Set<Integer> favorites = userRepository.findFavoritesByUserId(userId);
        List<Games> list = new ArrayList<>();
        for (Integer i : favorites){

            Games game = gamesRepository.findGameByAppId(i);
            list.add(game);
        }
        return new PageImpl<>(list);
    }
}
