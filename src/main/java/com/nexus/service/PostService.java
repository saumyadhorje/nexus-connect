package com.nexus.service;

import com.nexus.dto.CreatePostRequest;
import com.nexus.dto.UpdatePostRequest;
import com.nexus.entity.Post;
import com.nexus.entity.User;

import java.util.List;

public interface PostService {

    // Create Post
    Post createPost(CreatePostRequest request, User user);

    // Get All Posts
    List<Post> getAllPosts();

    // Get Post By ID
    Post getPostById(Long id);

    Post updatePost(Long id,
                    UpdatePostRequest request,
                    User user);

    void deletePost(Long id, User loggedInUser);
}