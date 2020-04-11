package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @RequestMapping("/about")
    String about() {
        return "about";
    }

    @RequestMapping("/addpost")
    public String addPost(Model model) {
        model.addAttribute("addPost", new Post());
        return "addpost";
    }

    @PostMapping("/addPost")
    public String addPost(Model model, Post post){
        postService.addPost(post);
        return ("redirect:/");
    }
}