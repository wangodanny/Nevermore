package com.makersacademy.nevermore.controller;

import com.makersacademy.nevermore.model.Authority;
import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.Cost;
import com.makersacademy.nevermore.model.User;
import com.makersacademy.nevermore.repository.AuthoritiesRepository;
import com.makersacademy.nevermore.repository.CostRepository;
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
public class CostController {

@Autowired
CostRepository costRepository;

@GetMapping("/costs/new")
public String addCost (Model model) {
model.addAttribute("cost", new Cost());
return "costs/new";
}

}
    

