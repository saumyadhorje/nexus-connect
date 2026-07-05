package com.nexus.controller;

import com.nexus.dto.CreatePostRequest;
import com.nexus.dto.UpdatePostRequest;
import com.nexus.entity.Post;
import com.nexus.entity.User;
import com.nexus.service.PostService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Post> createPost(
            @Valid @RequestBody CreatePostRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Post post = postService.createPost(request, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }

    // Get All Posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {

        List<Post> posts = postService.getAllPosts();

        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {

        Post post = postService.getPostById(id);

        return ResponseEntity.ok(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePostRequest request,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Post updatedPost = postService.updatePost(id, request, user);

        return ResponseEntity.ok(updatedPost);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        postService.deletePost(id, user);

        return ResponseEntity.noContent().build();
    }
}