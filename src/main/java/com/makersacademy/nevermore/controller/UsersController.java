package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/login")
    public String signin(Model model) {
        return "/logon";
    }

    @GetMapping("/errorUser")
    public String errorUsername(Model model) {
        return "/errorUser";
    }


    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        Optional<User> username = userRepository.findByUsername(user.getUsername());

        if (username.isPresent() == true) {
            return new RedirectView("/errorUser");
        }
        else {
            userRepository.save(user);
            System.out.println("we are in the post request");
            System.out.println(user);    
        }

        return new RedirectView("/login");
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "/dashboard";
    }
    
}
