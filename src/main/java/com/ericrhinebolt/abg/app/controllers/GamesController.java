package com.ericrhinebolt.abg.app.controllers;

import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.service.GamesService;
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

    @GetMapping("/games/{search}")
    @ResponseBody
    public List<Games> findGamesByTitle(@PathVariable String search) {
        return gamesService.searchGames(search);
    }

    @GetMapping("/games")
    public String games(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        model.addAttribute("games", gamesService.getPage(pageNumber, size));
        return "games";
    }

    @ModelAttribute("game")
    public List<Games> game() {
        return gamesService.findAll();
    }

    @GetMapping("/games/search_results/{search}")
    public String searchResults(@PathVariable String search,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "50") int size, Model model) {
        model.addAttribute("game", gamesService.getPage(pageNumber, size, search));
        return "search_results";
    }

    @GetMapping("/games/game_info/{appid}")
    public String gameInfo(@PathVariable String appid, Model model){
        model.addAttribute("appId", appid);
        return "game_info";
    }

}

