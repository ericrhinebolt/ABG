package com.ericrhinebolt.abg.ABG.controllers;

import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.service.GamesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class GamesController {

    private final GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

//    Mapping for searching games
    @GetMapping("/games/{search}")
    @ResponseBody
    public List<Games> findGamesByTitle(@PathVariable String search) {
        return gamesService.searchGames(search);
    }

//    Mapping for full list of games
    @GetMapping("/games")
    public String games(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        model.addAttribute("games", gamesService.getPage(pageNumber, size));
        return "games";
    }

//    @ModelAttribute("game")
//    public List<Games> game() {
//        return gamesService.findAll();
//    }

//    Mapping for returning search results for games
    @GetMapping("/games/search_results/{search}")
    public String searchResults(@PathVariable String search,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        model.addAttribute("game", gamesService.getPage(pageNumber, size, search));
        return "search_results";
    }

//    Mapping for getting game info
    @GetMapping("/games/game_info/{appid}")
    public String gameInfo(@PathVariable String appid, Model model){
        model.addAttribute("appId", appid);
        return "game_info";
    }

}

