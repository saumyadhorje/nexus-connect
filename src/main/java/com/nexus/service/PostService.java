package com.nexus.service;

import com.nexus.dto.request.CreatePostRequest;
import com.nexus.dto.request.UpdatePostRequest;
import com.nexus.dto.response.PostResponse;

import com.nexus.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    // Create Post
    //Post createPost(CreatePostRequest request, User user);
    PostResponse createPost(CreatePostRequest request, User user);

    // Get All Posts
    Page<PostResponse> getAllPosts(int page, int size, String sortDir);

    // Get Post By ID
    PostResponse getPostById(Long id);


    // Post updatePost(Long id,
      //              UpdatePostRequest request,
      //              User user);
    PostResponse updatePost(Long id, UpdatePostRequest request, User user);

    void deletePost(Long id, User loggedInUser);
    List<PostResponse> searchPosts(String keyword);
}