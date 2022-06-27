package com.ericrhinebolt.abg.ABG.controllers;

import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.Games;
import com.ericrhinebolt.abg.ABG.models.User;
import com.ericrhinebolt.abg.ABG.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

//    Mapping for index, returns basic index if not logged in, and news feed if logged in.
    @RequestMapping("/index")
    public String index(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){

        System.out.println(userDetails);
        if(userDetails != null) {
            String username = userDetails.getUsername();
            System.out.println(username);
            User u = userRepository.findByUserName(username);
            Set<Games> favorites = u.getFavoriteGames();
            model.addAttribute("favNews", favorites);
        }
        return "index";
    }

}
