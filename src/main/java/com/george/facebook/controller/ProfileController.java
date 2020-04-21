package com.george.facebook.controller;

import com.george.facebook.model.Post;
import com.george.facebook.model.Profile;
import com.george.facebook.model.User;
import com.george.facebook.service.PostService;
import com.george.facebook.service.ProfileService;
import com.george.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("{id}")
    public String getProfile(Model model, @PathVariable Long id, HttpServletRequest request){
        List<Post> posts = postService.findAllByUserId(id);
//        List<Post> posts = postService.findAllDesc();
        int postCount = posts.size();
        model.addAttribute("posts", posts);
        model.addAttribute("postCount", postCount);
        // loggein in user
        Long userId = getUserId();
        model.addAttribute("userId", userId);

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        // profile visited
        User profile = userService.findById(id);
        profile.setPassword("");
        model.addAttribute("profile", profile);

        Profile bio = profileService.findById(id);
        model.addAttribute("bio", bio);

        // redirect an obj from another controller AuthController
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null){
//            String emailId =  (String) flashMap.get("email");
//            System.out.println(emailId);
            model.addAttribute("email", user.getEmail());
        }
        return "profile/profile";
    }

    @GetMapping("/new")
    public String newProfile(Model model){
        Long usedId = getUserId();
//        if (usedId != id)
//            id = usedId;
//        Profile profile = profileService.findById(id);
//        profile.getBio();
//        profile.getCity();
        model.addAttribute("profile", new Profile());
        return "profile/edit-profile";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model,@PathVariable Long id){
        Long usedId = getUserId();
        if (usedId != id)
            id = usedId;
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("id", id);

        Long userId = getUserId();
        model.addAttribute("userId", userId);

        Profile profile = profileService.findById(id);
        model.addAttribute("profile", profile);

        return "profile/edit-profile";
    }


    @PostMapping
    public String postProfile(Model model, Profile profile){
        Long usedId = getUserId();
        Profile newProfile = profileService.save(profile);
        if (newProfile == null) {
            return "/profile/edit/" + usedId;
        }
        return "redirect:/profile/" + usedId;
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
