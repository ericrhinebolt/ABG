package com.ericrhinebolt.abg.ABG.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserProfileController {
//    Mapping to user profile
    @RequestMapping("/user_profile")
    public String userProfile(){
        return "user_profile";
    }
}
