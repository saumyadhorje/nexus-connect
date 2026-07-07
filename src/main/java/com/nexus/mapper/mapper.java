package com.nexus.mapper;

import com.nexus.dto.response.PostResponse;
import com.nexus.dto.response.UserResponse;
import com.nexus.entity.Post;
import com.nexus.entity.User;

public class mapper {

    public static UserResponse toUserResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBio()
        );
    }

    public static PostResponse toPostResponse(Post post) {

        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                toUserResponse(post.getUser())
        );
    }
}