package com.sparta.spartaposts.dto;

import com.sparta.spartaposts.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String author;
    private String content;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
    }
}
