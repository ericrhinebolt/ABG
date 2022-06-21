package com.ericrhinebolt.abg.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserProfileController {
    @RequestMapping("/user_profile")
    public String userProfile(){
        return "user_profile";
    }
}
