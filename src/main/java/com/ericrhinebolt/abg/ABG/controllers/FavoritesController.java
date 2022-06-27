package com.ericrhinebolt.abg.ABG.controllers;

import com.ericrhinebolt.abg.ABG.data.GamesRepository;
import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.models.User;
import com.ericrhinebolt.abg.ABG.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//    Mapping for favorites view redirect
    @RequestMapping("/favorites")
    public String favorites(){
        return "redirect:/favorites/{username}";
    }

//    Mapping for adding favorites - checks if user has a set of games, if not, makes new set.
    @PostMapping("/add_favorite")
    public String addFavorite(@RequestParam(value = "appId") int appId,
                              @RequestParam(value = "userId") String userId, RedirectAttributes redirectAttributes){
        User u = userRepository.findByUserName(userId);
        Games g = gamesRepository.findGameByAppId(appId);
        String username = userId;
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
        redirectAttributes.addFlashAttribute("success", "Game added to favorites.");
        return "redirect:/favorites/" + username;
    }

//    Mapping for favorites view
    @GetMapping("/favorites/{username}")
    public String searchResults(@PathVariable String username,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        User u = userRepository.findByUserName(username);
        int userId = u.getUserId();
        model.addAttribute("favorites", gamesService.getPage(pageNumber, size, userId));
        return "favorites";
    }

//    Mapping for removing favorites
    @DeleteMapping("/favorites/delete/{appId}")
    public String deleteFavorite(@PathVariable int appId, @RequestParam (value = "userId") String userId, RedirectAttributes redirectAttributes) {
        User u = userRepository.findByUserName(userId);
        Games g = gamesRepository.findGameByAppId(appId);
        String username = userId;
        Set<Games> favorites = u.getFavoriteGames();
        favorites.remove(g);
        u.setFavoriteGames(favorites);
        userRepository.save(u);
        redirectAttributes.addFlashAttribute("success", "Game successfully removed.");
        return "redirect:/favorites/" + username;
    }

}

