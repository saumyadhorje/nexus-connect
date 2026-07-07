package com.nexus.serviceImpl;

import com.nexus.dto.request.CreatePostRequest;
import com.nexus.dto.request.UpdatePostRequest;
import com.nexus.dto.response.PostResponse;
import com.nexus.entity.Post;
import com.nexus.entity.User;
import com.nexus.exception.PostNotFoundException;
import com.nexus.mapper.mapper;
import com.nexus.repository.PostRepository;
import com.nexus.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponse createPost(CreatePostRequest request, User user) {

        Post post = new Post();
        post.setContent(request.getContent());
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return mapper.toPostResponse(savedPost);
    }

    @Override
    public Page<PostResponse> getAllPosts(int page, int size, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by("createdAt").ascending()
                : Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository
                .findAll(pageable)
                .map(mapper::toPostResponse);
    }

    @Override
    public PostResponse getPostById(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        return mapper.toPostResponse(post);
    }

    @Override
    public PostResponse updatePost(Long id,
                                   UpdatePostRequest request,
                                   User loggedInUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(loggedInUser.getId())) {
            throw new AccessDeniedException("You are not allowed to update this post");
        }

        post.setContent(request.getContent());

        Post savedPost = postRepository.save(post);

        return mapper.toPostResponse(savedPost);
    }

    @Override
    public void deletePost(Long id, User loggedInUser) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        if (!post.getUser().getId().equals(loggedInUser.getId())) {
            throw new AccessDeniedException("You are not allowed to delete this post");
        }

        postRepository.delete(post);
    }

    @Override
    public List<PostResponse> searchPosts(String keyword) {

        return postRepository
                .findByContentContainingIgnoreCaseOrderByCreatedAtDesc(keyword)
                .stream()
                .map(mapper::toPostResponse)
                .toList();
    }
}