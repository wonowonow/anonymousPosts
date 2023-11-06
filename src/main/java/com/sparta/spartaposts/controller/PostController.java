package com.sparta.spartaposts.controller;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private HttpHeaders makeUTF8Header(){
        HttpHeaders returnResHeaders = new HttpHeaders();
        returnResHeaders.add("Content-Type", "application/json;charset=UTF-8");

        return returnResHeaders;
    }

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
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostService postService = new PostService();
        try {
            postService.updatePost(id, postRequestDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), makeUTF8Header(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id + "번이 정상적으로 수정 되었습니다.", makeUTF8Header(), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostService postService = new PostService();
        try {
            postService.deletePost(id, postRequestDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), makeUTF8Header(), HttpStatus.BAD_REQUEST);
            // 로그 변환해서 e.getMessage를 스트링으로 변환해야함
        }
        return new ResponseEntity<>(id + "번이 정상적으로 삭제 되었습니다", makeUTF8Header(), HttpStatus.OK);
        // 리스폰스 엔티티
    }
}
