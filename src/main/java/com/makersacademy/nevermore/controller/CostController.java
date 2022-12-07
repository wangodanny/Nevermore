package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.Authority;
import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.AuthoritiesRepository;
import com.makersacademy.nevermore.repository.CostRepository;
import com.makersacademy.nevermore.repository.UserRepository;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeStamp);
        int monthInt = cal.get(Calendar.MONTH) + 1;
        String month = Month.of( monthInt ).getDisplayName( TextStyle.FULL , Locale.US );
        cost.setDate(timeStamp);
        cost.setMonth(month);
        costRepository.save(cost);

        System.out.println(month);

        return new RedirectView("/dashboard");
    }

    @GetMapping("/costs/all")
    public String show(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 
        
        // Iterable<Cost> costs = costRepository.findAll();

        // ArrayList<Cost> relatedCosts = new ArrayList<>();

        
        // model.addAttribute("costs", relatedCosts);
        // model.addAttribute("cost", new Cost());

        model.addAttribute("content", userObj.getContentInList());
        model.addAttribute("subs", userObj.getPricesInList());
        // model.addAttribute("post", new Post());
        return "costs/all";
    }
    @DeleteMapping("/costs/{id}")
    public RedirectView delete(@PathVariable("id") Long id) {
        //content not being passed in
        // System.out.println(cost);
        // System.out.println("CONTENT HERE:::::");
        // System.out.println(content);
        //content is null thus this doesnt work
        Optional<Cost> del = costRepository.findById(id);
        Cost costObj = del.get(); 
        costRepository.delete(costObj);
        System.out.println(costObj);
        return new RedirectView("/dashboard");
    }
}

