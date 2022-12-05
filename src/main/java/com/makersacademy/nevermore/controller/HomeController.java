package com.makersacademy.nevermore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.UserRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/")
	public String index(Model model, Principal principal) {
	
		return "/logon";
	}

	@RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {


        

    String currentUsername = principal.getName();
            
    java.util.Optional<User> currentUser = userRepository.findByUsername(currentUsername);
    User me = currentUser.get();

    model.addAttribute("user", me.getImage());
    return "/dashboard";
        }
        
	
}