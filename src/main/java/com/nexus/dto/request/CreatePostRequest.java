package com.nexus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {

    @NotBlank(message = "Post content cannot be empty")
    @Size(max = 1000, message = "Post cannot exceed 1000 characters")
    private String content;

    public CreatePostRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}