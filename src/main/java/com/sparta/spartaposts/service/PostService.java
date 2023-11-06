package com.sparta.spartaposts.service;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.entity.Post;
import com.sparta.spartaposts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PostService {

    public PostResponseDto creatPost(PostRequestDto postRequestDto) {
        // requestDto -> Entity
        Post post = new Post(postRequestDto);
        post.setCreatedAt(LocalDateTime.now());

        PostRepository postRepository = new PostRepository();
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {

        PostRepository postRepository = new PostRepository();
        return postRepository.findAll();
    }

    public PostResponseDto getPost(Long id) {
        PostRepository postRepository = new PostRepository();
        return postRepository.findPost(id);
    }

    public Long updatePost(Long id, PostRequestDto postRequestDto) {
        PostRepository postRepository = new PostRepository();
        return postRepository.editPost(id, postRequestDto);
    }

    public Long deletePost(Long id, PostRequestDto postRequestDto) {
        PostRepository postRepository = new PostRepository();
        return postRepository.delete(id, postRequestDto);
    }
}
