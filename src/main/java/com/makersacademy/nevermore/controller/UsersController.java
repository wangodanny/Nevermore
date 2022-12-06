package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.Authority;
import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.AuthoritiesRepository;
import com.makersacademy.nevermore.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.mapping.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/users/new")
    public String signup(@RequestParam(name= "error", required = false) Optional<String> errorOptional, Model model) {
       String errorMsg = "";
       if(errorOptional.isPresent()) {
        String errorParam = errorOptional.get().toString();
        if(errorParam.equals("username_taken")) {
            errorMsg = "That username has been taken, please choose another one!";
        }
       }
        model.addAttribute("user", new User());
        model.addAttribute("errorMsg", errorMsg);
        return "users/new";
    }

    @GetMapping("/logon")
    public String signin(Model model) {
        return "/logon";
    }

    @GetMapping("/logon-error")
    public String signin_e(Model model) {
        return "/logon_e";
    }

    @GetMapping("/errorUser")
    public String errorUsername(Model model) {
        return "/errorUser";
    }


    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, Principal principal) {
        Optional<User> username = userRepository.findByUsername(user.getUsername());

        if (username.isPresent() == true) {
            return new RedirectView("/users/new?error=username_taken");
        }
        else {
            userRepository.save(user);
            System.out.println("we are in the post request");
            System.out.println(user);
    
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);

            
        }

        return new RedirectView("/logon");
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 

        
        model.addAttribute("content", userObj.getContentInList());
        model.addAttribute("subs", userObj.getPricesInList());
      
        

        List<String> prices = userObj.getPricesInList();
        List<String> names = userObj.getContentInList();

        Map<String, Double> pieDataList =  new HashMap<>();

        Double Sum = Double.valueOf(0);
        
        for(int i=0; i<prices.size(); i++){
            //setting the sum
            Double price = Double.valueOf(prices.get(i));
            Sum += price;
            Sum =  Math.floor(Sum * 100) / 100;
            //setting the keyValue list
            String cont = String.valueOf(names.get(i));
             pieDataList.put(cont, price);
        }

        Double remaining = userObj.getSalary() - Sum;

        model.addAttribute("sum", Sum);
        model.addAttribute("remaining", remaining);
        modelMap.addAttribute("pieDataList", pieDataList);
       


        List<String> dates = userObj.getDateInList();

        Map<String, Float> barDataList =  new HashMap<>();

        for(int i=0; i<prices.size(); i++){
            //checks if the current month is stored in the hashmap
            if(barDataList.containsKey(dates.get(i))){
                Float temp = barDataList.get(dates.get(i));
                temp += Float.valueOf(prices.get(i));
                barDataList.put(dates.get(i), temp);
            }else{
                Float price = Float.valueOf(prices.get(i));
                barDataList.put(dates.get(i), price);
            }

        }
        
        modelMap.addAttribute("barDataList", barDataList);


        return "/dashboard";
    }

}
