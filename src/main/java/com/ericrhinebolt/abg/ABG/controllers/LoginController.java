package com.ericrhinebolt.abg.ABG.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    Mapping for login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    Mapping for logout
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

}
