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

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    private HttpHeaders makeUTF8Header(){
        HttpHeaders returnResHeaders = new HttpHeaders();
        returnResHeaders.add("Content-Type", "application/json;charset=UTF-8");

        return returnResHeaders;
    }

    @PostMapping("/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.creatPost(postRequestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<String> getPost(@PathVariable Long id) {
        try{
            postService.getPost(id);
        }
        catch (NullPointerException | IllegalArgumentException e) {
            return new ResponseEntity<>("존재하지 않는 게시글입니다.", makeUTF8Header(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(String.valueOf(id), makeUTF8Header(), HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        try {
            postService.updatePost(id, postRequestDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), makeUTF8Header(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id + "번이 정상적으로 수정 되었습니다.", makeUTF8Header(), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        try {
            postService.deletePost(id, postRequestDto);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), makeUTF8Header(), HttpStatus.BAD_REQUEST);
            // 로그 변환해서 e.getMessage를 스트링으로 변환해야함
        }
        return new ResponseEntity<>("게시글 번호 "+id + "번이 정상적으로 삭제 되었습니다", makeUTF8Header(), HttpStatus.OK);
        // 리스폰스 엔티티
    }
}
