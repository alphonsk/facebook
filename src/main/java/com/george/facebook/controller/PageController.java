package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        return "home";
    }

    @RequestMapping("/about")
    String about() {
        return "about";
    }

    @RequestMapping("/addpost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "addpost";
    }

    @PostMapping("/addpost")
    // also in model & form
    public String addPost(@Valid Post post, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "addpost";
        }
        postService.addPost(post);
        return ("redirect:/");
    }



    //
}