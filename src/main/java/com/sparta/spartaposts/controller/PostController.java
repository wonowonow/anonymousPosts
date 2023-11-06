package com.sparta.spartaposts.controller;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @PostMapping("/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto postRequestDto) {
        PostService postService = new PostService();
        return postService.creatPost(postRequestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        PostService postService = new PostService();
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        PostService postService = new PostService();
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostService postService = new PostService();
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostService postService = new PostService();
        return postService.deletePost(id, postRequestDto);
    }

}
