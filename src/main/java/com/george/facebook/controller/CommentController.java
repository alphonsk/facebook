package com.george.facebook.controller;

import com.george.facebook.model.Comment;
import com.george.facebook.model.Post;
import com.george.facebook.model.Profile;
import com.george.facebook.model.User;
import com.george.facebook.service.CommentService;
import com.george.facebook.service.PostService;
import com.george.facebook.service.ProfileService;
import com.george.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;


    // new comment
    @GetMapping
    public String newComment(Model model) {
        Profile profile = profileService.findById(getUserId());
        model.addAttribute("profile", profile);
        model.addAttribute("postId", 11);
        model.addAttribute("userId", profile.getId());
        model.addAttribute("comment", new Comment());
        return "comment/new-comment";
    }

    // save
//    @PostMapping("/addpost")
//    // also in model & form
//    public String addPost(@Valid Post post, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()) {
//            return "post/addpost";
//        }
//
//        Post newPost = postService.save(post);
//        if (post == null){
//            return "post/addpost";
//        }
//        return ("redirect:/posts");
//    }

    // save new comment
    @PostMapping("/new")
    public String saveComment(@Valid Comment comment, BindingResult bindingResult, Model model) {


//        return null;
        model.addAttribute("comment", new Comment());
        model.addAttribute("message", comment.getText());
        Comment newComment = commentService.save(comment);
        if (comment == null){
            return "comment/new-comment";
        }
        return "redirect:/comment";
    }






    // private methods
    //
    private boolean isAuthenticated(){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth == "anonymousUser" )
            return false;
        return true;
    }

    private Long  getUserId(){
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
