package com.george.facebook.service;

import com.george.facebook.model.Comment;
import com.george.facebook.model.Post;
import com.george.facebook.model.Profile;
import com.george.facebook.model.User;
import com.george.facebook.repository.CommentRepository;
import com.george.facebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final static int PAGESIZE = 3;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;


    // save new comment
    public Comment save(Comment comment, Post post) {
        Profile profile = profileService.findById(getUserId());
        comment.setProfile(profile);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
//        System.out.println(" ");
//        System.out.println("comment sercice new coment id " + newComment.getId());
//        System.out.println(" ");
        return newComment;
    }

    // save new comment
    public Comment save(Comment comment) {
        Profile profile = profileService.findById(getUserId());
        comment.setProfile(profile);
        comment.setPost(comment.getPost());
        Comment newComment = commentRepository.save(comment);
        return newComment;
    }


    // delete comment
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }



    public List<Comment> findAllDesc(){

        return commentRepository.findAllByOrderByAddedDesc();
    }


//    public List<Post> findAllByUserId(Long id) {
//        List<Post> postList = new ArrayList<>();
//        Iterable<Post> posts = postRepository.findAllByProfileIdOrderByIdDesc(id);
//        posts.forEach(postList::add);
//        return postList;
//    }
//
//
//    public List<Post> findAll() {
//        List<Post> postList = new ArrayList<>();
//        Iterable<Post> posts = postRepository.findAll();
//        posts.forEach(postList::add);
//        return postList;
//    }

    //
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
