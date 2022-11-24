package com.makersacademy.acebook.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendshipRepository;
import com.makersacademy.acebook.repository.UserRepository;

@Controller

public class FriendshipsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendshipRepository friendshipRepository;

    @PostMapping("/friendships")
    public RedirectView create(@RequestParams Long receiver, @ModelAttribute Friendship friendship, Principal principal) {
        Iterable<Friendship> allFriendships = friendshipRepository.findAll();

        String name = principal.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 
        Long userId = userObj.getId();
        friendship.senderSetter(userId);
        friendship.receiverSetter(receiver);

        User senderObj = userRepository.findById(userId).get();
        friendship.senderUsernameSetter(senderObj.getUsername());
        
        // get or request check if it's included in the database
        Boolean checkInclusion = false;
        for (Friendship f: allFriendships) {
            if (f.receiverGetter() == friendship.receiverGetter() && f.senderGetter() == friendship.senderGetter()) {
                checkInclusion = true;
                break;
            }
        }

        if (checkInclusion != true) {
            friendshipRepository.save(friendship);
        }

        return new RedirectView("/posts");
    }

    @GetMapping("/requests")
    public String request(Model model, Principal principal) {
        Iterable<Friendship> allFriendships = friendshipRepository.findAll();

        String name = principal.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 
        Long userId = userObj.getId();

        List<Friendship> pendingRequests = new ArrayList<>();

        for (Friendship f: allFriendships) {
            System.out.printf("status: %s", f.statusGetter());
            if (f.receiverGetter() == userId && f.statusGetter().equals("pending")) {
                pendingRequests.add(f);
            }
        }

        model.addAttribute("friendships", pendingRequests);
        model.addAttribute("friendship", new Friendship());

        return "/friendships/requests";
    }

    @PostMapping("/rejected")
    public RedirectView reject(@RequestParams Long friendshipId) {
        Friendship request = friendshipRepository.findById(friendshipId).get();
        request.statusSetter("rejected");
        friendshipRepository.save(request);
        return new RedirectView("/posts");
    }

    @PostMapping("/accepted")
    public RedirectView accept(@RequestParams Long friendshipId) {
        Friendship request = friendshipRepository.findById(friendshipId).get();
        request.statusSetter("accepted");
        friendshipRepository.save(request);
        return new RedirectView("/posts");
    }
    
}
