package com.nexus.serviceImpl;

import com.nexus.dto.CreatePostRequest;
import com.nexus.dto.UpdatePostRequest;
import com.nexus.entity.Post;
import com.nexus.entity.User;
import com.nexus.exception.PostNotFoundException;
import com.nexus.repository.PostRepository;
import com.nexus.service.PostService;
import org.springframework.stereotype.Service;
import com.nexus.dto.UpdatePostRequest;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(CreatePostRequest request, User user) {

        Post post = new Post();

        post.setContent(request.getContent());
        post.setUser(user);

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc();

    }
    @Override
    public Post getPostById(Long id) {

        return postRepository.findById(id)
               // .orElseThrow(() -> new RuntimeException("Post not found"));
        .orElseThrow(() -> new PostNotFoundException("Post not found"));

    }
    @Override
    public Post updatePost(Long id,
                           UpdatePostRequest request,
                           User loggedInUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        // Check if the logged-in user owns the post
        if (!post.getUser().getId().equals(loggedInUser.getId())) {
            throw new AccessDeniedException("You are not allowed to update this post");
        }

        post.setContent(request.getContent());

        return postRepository.save(post);
    }
    @Override
    public void deletePost(Long id, User loggedInUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        // Only the owner can delete
        if (!post.getUser().getId().equals(loggedInUser.getId())) {
            throw new AccessDeniedException("You are not allowed to delete this post");
        }

        postRepository.delete(post);
    }
}