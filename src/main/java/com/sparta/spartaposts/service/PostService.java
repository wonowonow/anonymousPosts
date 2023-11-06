package com.sparta.spartaposts.service;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.entity.Post;
import com.sparta.spartaposts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto creatPost(PostRequestDto postRequestDto) {
        // requestDto -> Entity
        Post post = new Post(postRequestDto);
        post.setCreatedAt(LocalDateTime.now());

        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll();
    }

    public PostResponseDto getPost(Long id) {
        return postRepository.findPost(id);
    }

    public Long updatePost(Long id, PostRequestDto postRequestDto) {
        return postRepository.editPost(id, postRequestDto);
    }

    public Long deletePost(Long id, PostRequestDto postRequestDto) {
        return postRepository.delete(id, postRequestDto);
    }
}
