package com.george.facebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    String home() {
        return "home";
    }

    @RequestMapping("/about")
    String about() {
        return "about";
    }

    @RequestMapping("/addpost")
    String addPost() {
        return "addpost";
    }
}