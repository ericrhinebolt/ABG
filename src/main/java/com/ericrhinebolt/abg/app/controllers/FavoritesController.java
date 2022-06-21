package com.ericrhinebolt.abg.app.controllers;

import com.ericrhinebolt.abg.app.data.GamesRepository;
import com.ericrhinebolt.abg.app.data.UserRepository;
import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.models.User;
import com.ericrhinebolt.abg.app.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
public class FavoritesController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private final GamesService gamesService;

    public FavoritesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }


    @RequestMapping("/favorites")
    public String favorites(){
        return "redirect:/favorites/{username}";
    }

    @PostMapping("/add_favorite")
    public String addFavorite(@RequestParam(value = "appId") int appId,
                              @RequestParam(value = "userId") String userId){
        User u = userRepository.findByUserName(userId);
        Games g = gamesRepository.findGameByAppId(appId);
        if(u.getFavoriteGames() == null) {
            Set<Games> favorites = new HashSet<>();
            favorites.add(g);
            u.setFavoriteGames(favorites);
            userRepository.save(u);
        } else if (u.getFavoriteGames() != null) {
            Set<Games> favorites = u.getFavoriteGames();
            favorites.add(g);
            u.setFavoriteGames(favorites);
            userRepository.save(u);
        }
        return "redirect:/favorites";
    }

    @GetMapping("/favorites/{username}")
    public String searchResults(@PathVariable String username,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        User u = userRepository.findByUserName(username);
        int userId = u.getUserId();
        model.addAttribute("favorites", gamesService.getPage(pageNumber, size, userId));
        return "favorites";
    }

}

