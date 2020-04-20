package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.model.User;
import com.george.facebook.service.PostService;
import com.george.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping("{id}")
    public String getProfile(Model model, @PathVariable Long id){
        List<Post> posts = postService.findAllByUserId(id);
//        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        //
        Long userId = getUserId();
        model.addAttribute("userId", userId);

        User profile = userService.findById(id);
        model.addAttribute("profile", profile);
//        if (userId != id){
//            System.out.println(" ");
//            System.out.println(" ");
//            System.out.println(" user id n equal " + userId + "id" + id);
//            return "redirect:/";
//        }

        return "profile/profile";


    }



    //
    public Long  getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userService.findByEmail(email);
        if (user != null) {
            Long userId = user.getId();
            return userId;
        }
        return 0l;
    }

    //

}
