package com.george.facebook.service;

import com.george.facebook.model.Post;
import com.george.facebook.repository.PostRepository;
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

    public void addPost(Post post) {
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




    //
}
