package com.nexus.controller;

import com.nexus.dto.request.CreatePostRequest;
import com.nexus.dto.request.UpdatePostRequest;
import com.nexus.dto.response.PostResponse;
import com.nexus.entity.User;
import com.nexus.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create Post
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestBody CreatePostRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        PostResponse post = postService.createPost(request, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // Get All Posts
    @GetMapping
    public Page<PostResponse> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "desc") String sort) {

        return postService.getAllPosts(page, size, sort);
    }
    @GetMapping("/search")
    public List<PostResponse> searchPosts(
            @RequestParam String keyword) {

        return postService.searchPosts(keyword);
    }
    // Get Post By ID
    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // Update Post
    @PutMapping("/{id}")
    public PostResponse updatePost(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePostRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return postService.updatePost(id, request, user);
    }

    // Delete Post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        postService.deletePost(id, user);

        return ResponseEntity.noContent().build();
    }

}