package com.nexus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostRequest {

    @NotBlank(message = "Content cannot be empty")
    private String content;

}