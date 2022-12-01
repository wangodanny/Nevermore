package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.Authority;
import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.AuthoritiesRepository;
import com.makersacademy.nevermore.repository.CostRepository;
import com.makersacademy.nevermore.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CostController {

@Autowired
CostRepository costRepository;
@Autowired
UserRepository userRepository;

@GetMapping("/costs/new")
public String addCost (Model model) {
model.addAttribute("cost", new Cost());
return "costs/new";
}

@PostMapping("/costs")
    public RedirectView create(@ModelAttribute Cost cost, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName()).get();
        cost.setUser(currentUser);
        costRepository.save(cost);
        return new RedirectView("/dashboard");
    }


    @GetMapping("/costs/{id}")
    public String show(@PathVariable Long id, Model model){
        
        Iterable<Cost> costs = costRepository.findAll();

        ArrayList<Cost> relatedCosts = new ArrayList<>();

        
        model.addAttribute("costs", relatedCosts);
        model.addAttribute("cost", new Cost());
        // model.addAttribute("post", new Post());
        return "posts/show";
    }
}

