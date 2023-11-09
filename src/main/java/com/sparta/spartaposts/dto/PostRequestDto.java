package com.sparta.spartaposts.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;
}
