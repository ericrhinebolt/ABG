package com.ericrhinebolt.abg.ABG.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
}
