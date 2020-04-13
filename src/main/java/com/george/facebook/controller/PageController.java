package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

//    @RequestMapping("/")
//    public String home(Model model) {
//        List<Post> posts = postService.findAllDesc();
//        int postCount = posts.size();
//        model.addAttribute("posts", posts);
//        model.addAttribute("postCount", postCount);
//        return "home";
//    }

//    @RequestMapping(value = "/viewstatus", method=RequestMethod.GET)
//    ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p", defaultValue="1") int pageNumber) {
//
//        Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
//        modelAndView.getModel().put("page", page);
//
//        modelAndView.setViewName("app.viewStatus");
//
//        return modelAndView;
//    }

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
//        List<int> p

        return "posts";
    }

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        return "home";
    }

    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable Long id){
        Post post = postService.finDById(id);
//        post.setText();
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/editpost/{id}")
    public String editPost(Model model, @PathVariable Long id){
        Post post = postService.finDById(id);
//        post.setText();
        model.addAttribute("post", post);
        return "editpost";
    }



    @PostMapping("/editpost/{id}")
    public String editPost(@Valid Post post, BindingResult bindingResult, Model model, @PathVariable Long id){
        Post myPost = postService.finDById(id);
        if (bindingResult.hasErrors()) {
            return "editpost/" + id;
        }
        myPost.setId(id);
        myPost.setText(post.getText());
        myPost.setAdded(myPost.getAdded());
        postService.save(myPost);
        model.addAttribute("myPost", post);
        return "redirect:/post/" + id;
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
        postService.save(post);
        return ("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, @RequestParam(name="p", defaultValue="1") int pageNumber){
        Post post = postService.finDById(id);
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
}