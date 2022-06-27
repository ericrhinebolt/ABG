package com.ericrhinebolt.abg.ABG.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
//    Mapping for contact view
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
}
