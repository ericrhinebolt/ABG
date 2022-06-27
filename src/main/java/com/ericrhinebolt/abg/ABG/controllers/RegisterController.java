package com.ericrhinebolt.abg.ABG.controllers;

import com.ericrhinebolt.abg.ABG.data.UserRepository;
import com.ericrhinebolt.abg.ABG.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

//    Mapping for register
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

//    Mapping for processing registration
    @PostMapping("/process_register")
    public String processRegister(User user, RedirectAttributes redirectAttributes){
        if (!userRepository.existsByUserEmail(user.getUserEmail())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "register_success";
        } else {
            redirectAttributes.addFlashAttribute("userExists", "User with this email already exists!");
            return "redirect:/register";
        }

    }

}
