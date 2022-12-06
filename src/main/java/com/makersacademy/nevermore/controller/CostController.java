package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.Authority;
import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.AuthoritiesRepository;
import com.makersacademy.nevermore.repository.CostRepository;
import com.makersacademy.nevermore.repository.UserRepository;

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
    public RedirectView create(@ModelAttribute Cost cost) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 

        userObj.addCost(cost);
        costRepository.save(cost);
        userRepository.save(userObj);

        Date timeStamp = new Date();
        cost.setDate(timeStamp);
        costRepository.save(cost);

        System.out.println(userObj.getCosts());

        return new RedirectView("/dashboard");
    }

    @PostMapping("/delete")
    public RedirectView delete(@ModelAttribute Cost cost, String content) {
        //content not being passed in
        System.out.println("CONTENT HERE:::::");
        System.out.println(content);
        //content is null thus this doesnt work
        Optional<Cost> del = costRepository.findByContent(content);
        Cost costObj = del.get(); 
        costRepository.delete(costObj);
    
        return new RedirectView("/dashboard");
    }

    @GetMapping("/delete")
    public String showDelete(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 

        
        model.addAttribute("content", userObj.getContentInList());

        return "/temp";
    }


}

