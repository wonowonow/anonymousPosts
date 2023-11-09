package com.sparta.spartaposts.entity;

import com.sparta.spartaposts.dto.PostRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "author", nullable = false, length = 10)
    private String author;

    @Column(name = "password", nullable = false, length = 10)
    private String password;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.password = postRequestDto.getPassword();
        this.content = postRequestDto.getContent();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.content = postRequestDto.getContent();
    }

    public boolean updateBeforeCheck () {
        if (this.title.isEmpty()) {
            return true;
        } else if (this.author.isEmpty()) {
            return true;
        } else if (this.content.isEmpty()) {
            return true;
        } else if (this.password.isEmpty()) {
            return true;
        }
        return false;
    }
}
