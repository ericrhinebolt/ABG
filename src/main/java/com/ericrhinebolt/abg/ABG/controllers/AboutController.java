package com.ericrhinebolt.abg.ABG.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
//    Mapping for about view
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
