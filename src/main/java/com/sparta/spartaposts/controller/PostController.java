package com.sparta.spartaposts.controller;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class PostController {

    private final Map<Long, Post> postList = new HashMap<>();

    @PostMapping("/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto postRequestDto) {
        // requestDto -> Entity
        Post post = new Post(postRequestDto);
        post.setCreatedAt(LocalDateTime.now());

        // Post Max ID Check
        Long maxId = !postList.isEmpty() ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        // DB 저장
        postList.put(post.getId(), post);
        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        // Map To List
        List<PostResponseDto> responseDtoList = postList.values().stream()
                .map(PostResponseDto::new).toList();
        return responseDtoList;
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        Post post = postList.get(id);

        return new PostResponseDto(post);
    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        // 해당 포스트가 DB에 존재?
        if (Objects.equals(postList.get(id).getPassword(), postRequestDto.getPassword())) {
            if(postList.containsKey(id)) {
                Post post = postList.get(id);
                post.update(postRequestDto);
                return id;
            } else {
                throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        if (Objects.equals(postList.get(id).getPassword(), postRequestDto.getPassword())) {
            if(postList.containsKey(id)) {
                postList.remove(id);
                return id;
            } else {
                throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}
