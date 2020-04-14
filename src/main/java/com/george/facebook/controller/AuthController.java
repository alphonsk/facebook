package com.george.facebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/admin")
    String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }


    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

    @PostMapping("/login")
    public String getin(){
        return "posts";
    }




}