package com.george.facebook.service;

import com.george.facebook.model.Post;
import com.george.facebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Iterable<Post> posts = postRepository.findAll();
        posts.forEach(postList::add);
        return postList;
    }

    //
}
