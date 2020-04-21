package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.model.User;
import com.george.facebook.service.PostService;
import com.george.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    UserService userService;


    // all post
    @RequestMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        //
        Long userId = getUserId();
        model.addAttribute("userId", userId);
        return "post/all-posts";
    }

    // all pagination posts
    @RequestMapping("/posts")
    public String home(Model model, @RequestParam(name="p", defaultValue="1") int pageNumber) {
        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
//        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        //
        Page<Post> page = postService.getPage(pageNumber);
        model.addAttribute("page", page );
        model.addAttribute("pageNumber", pageNumber );

        //
        Long userId = getUserId();
        model.addAttribute("userId", userId);

        return "post/posts";
    }


    // add post
    @RequestMapping("/addpost")
    public String addPost(Model model) {
        Long userId = getUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("post", new Post());
        return "post/addpost";
    }


    // add post
    @PostMapping("/addpost")
    // also in model & form
    public String addPost(@Valid Post post, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "post/addpost";
        }

        Post newPost = postService.save(post);
        if (post == null){
            return "post/addpost";
        }
        return ("redirect:/posts");
    }


    // get post
    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable Long id){
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        //
        Long userId = getUserId();
        model.addAttribute("userId", userId);
        return "post/post";
    }


    // edit post
    @GetMapping("/editpost/{id}")
    public String editPost(Model model, @PathVariable Long id){
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        //
        Long userId = getUserId();
        model.addAttribute("userId", userId);
        return "post/editpost";
    }


    // save edit post
    @PostMapping("/editpost/{id}")
    public String editPost(@Valid Post post, BindingResult bindingResult, Model model, @PathVariable Long id){
        Post myPost = postService.findById(id);
        if (bindingResult.hasErrors()) {
            return "post/editpost/" + id;
        }
        myPost.setId(id);
        myPost.setText(post.getText());
        myPost.setAdded(myPost.getAdded());
        model.addAttribute("myPost", post);

        Post newPost = postService.save(myPost);
        if (post == null){
            return "post/editpost/" + id;
        }
        return "redirect:/post/" + id;
    }


    // delete post
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, @RequestParam(name="p", defaultValue="1") int pageNumber){
        Post post = postService.findById(id);
        postService.deleteById(id);

        //
        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("postCount", postCount);
        //
        Page<Post> page = postService.getPage(pageNumber);
        model.addAttribute("page", page );
        model.addAttribute("pageNumber", pageNumber );

        //
        return "redirect:/posts";
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