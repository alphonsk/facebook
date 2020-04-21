package com.george.facebook.service;

import com.george.facebook.model.Profile;
import com.george.facebook.model.Profile;
import com.george.facebook.model.User;
import com.george.facebook.repository.PostRepository;
import com.george.facebook.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService {

     
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserService userService;

    public Profile save(Profile profile) {
        Long userId = profile.getUser().getId();
        User user = userService.findById(userId);
        profile.setUser(user);
        profile.setId(userId);

        if (profile.getAdded()== null) {
            profile.setAdded(new Date());
        }
        Profile pro = profileRepository.save(profile);
        if (pro == null)
            return null;

        return pro;
    }

    public Profile findById(Long id){
        Profile profile = null;
        try {
            profile = profileRepository.findById(id).get();
        } finally {
            if (profile == null)
                return new Profile();
            return profile;
        }

    }

//    public List<Profile> findAll() {
//        List<Profile> postList = new ArrayList<>();
//        Iterable<Profile> posts = postRepository.findAll();
//        posts.forEach(postList::add);
//        return postList;
//    }
//
//    public List<Profile> findAllDesc(){
//        return postRepository.findAllByOrderByAddedDesc();
//    }
////    public Page<StatusUpdate> getPage(int pageNumber) {
////        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "added");
////
////        return statusUpdateDao.findAll(request);
////    }
//
//    public Page<Profile> getPage(int pageNumber) {
//        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "added");
//
//        return postRepository.findAll(request);
//    }
//
//    public Profile findById(Long id) {
//        Profile profile = null;
//        try {
//            profile = postRepository.findById(id).get();
//        } finally {
//
//            if(profile == null){
//                profile = postRepository.findTopByOrderByIdDesc();
//            }
//
//            return profile;
//        }
//
//    }
//
//    public void deleteById(Long id) {
//        postRepository.deleteById(id);
//    }
//
//
//    public List<Profile> findAllByUserId(Long id) {
//        List<Profile> postList = new ArrayList<>();
////        Iterable<Profile> posts = postRepository.findAllByUserId(id);
////        repository.findAll(Sort.by(Sort.Direction.DESC, "colName"));
//        Iterable<Profile> posts = postRepository.findAllByUserIdOrderByIdDesc(id);
//        posts.forEach(postList::add);
//        return postList;
//    }




    public String getE(String error){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println( error);
        return " ";
    }




    //
}
