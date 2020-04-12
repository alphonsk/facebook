package com.george.facebook.repository;

import com.george.facebook.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Post findFirstByOrderByAddedDesc();

    List<Post> findAllByOrderByAddedDesc();


}