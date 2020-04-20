package com.george.facebook.service;

import com.george.facebook.model.Post;
import com.george.facebook.model.User;
import com.george.facebook.repository.PostRepository;
import org.assertj.core.internal.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final static int PAGESIZE = 3;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public void save(Post post) {
        Long userId = post.getUser().getId();
        User user = userService.findById(userId);
        post.setUser(user);
        postRepository.save(post);
    }

    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Iterable<Post> posts = postRepository.findAll();
        posts.forEach(postList::add);
        return postList;
    }

    public List<Post> findAllDesc(){
        return postRepository.findAllByOrderByAddedDesc();
    }
//    public Page<StatusUpdate> getPage(int pageNumber) {
//        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "added");
//
//        return statusUpdateDao.findAll(request);
//    }

    public Page<Post> getPage(int pageNumber) {
        PageRequest request = PageRequest.of(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "added");

        return postRepository.findAll(request);
    }

    public Post finDById(Long id) {
        Post post = null;
        try {
            post = postRepository.findById(id).get();
        } finally {

            if(post == null){
                post = postRepository.findTopByOrderByIdDesc();
            }

            return post;
        }

    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public String getE(String error){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println( error);
        return " ";
    }


    //
}
