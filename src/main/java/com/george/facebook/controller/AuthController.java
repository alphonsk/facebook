package com.george.facebook.controller;

import com.george.facebook.model.User;
import com.george.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    //
    @GetMapping("/login")
    public String getLogin(Model model, User user){
        model.addAttribute("user", new User());
        return "register/login";
    }

    //
    @PostMapping("/login")
    public String getin(){

        return "post/posts";
    }

    //
    @GetMapping("/signup")
    public String getSignup(Model model){
        model.addAttribute("user", new User());
        return "register/signup";
    }

    //
    @PostMapping("/signup")
    public String registerUser(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()) {
//            model.addAttribute("user", new User());
            return "register/signup";
        }
        //
        User newUser = userService.save(user);
        if (newUser == null){
            model.addAttribute("invalidUser", user);
            return "register/signup";
        }
        return "redirect:/login";
    }

    //
    @RequestMapping(value="/admin")
    public String adminInfo(ModelMap model, Authentication authentication) {
        model.addAttribute("name", authentication.getName());
        return "admin/admin";
    }

    //
    @RequestMapping(value="/user")
    public String userInfo(ModelMap model, Authentication authentication) {
        model.addAttribute("name", authentication.getName());
        return "post/posts";
    }

//    @Override
//    @RequestMapping(value="/error")
//    public String error() {
//        return "access-denied";
//    }




//           model.addAttribute("email", email);
//        System.out.println(" ");
//        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
//        System.out.println("xxxx " + auth.getName());
//        System.out.println("xxxx " + auth.getDetails().toString())





}