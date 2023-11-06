package com.sparta.spartaposts.controller;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.entity.Post;
import com.sparta.spartaposts.service.PostService;
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
                .map(PostResponseDto::new).sorted(Comparator.comparing(PostResponseDto::getCreatedAt).reversed())
                .toList();
        return responseDtoList;
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        if (postList.containsKey(id)) {
            Post post = postList.get(id);
            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
        }
    }

    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        if (Objects.equals(postList.get(id).getPassword(), postRequestDto.getPassword())) {
            // 해당 포스트가 DB에 존재?
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

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        if (Objects.equals(postList.get(id).getPassword(), postRequestDto.getPassword())) {
            if(postList.containsKey(id)) {
                postList.remove(id);
                return id;
            } else {
                throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); // 3층 분리 후 컨트롤러에서 try catch로 잡아야 함!
        }
    }

}
