package com.sparta.spartaposts.service;

import com.sparta.spartaposts.dto.PostRequestDto;
import com.sparta.spartaposts.dto.PostResponseDto;
import com.sparta.spartaposts.entity.Post;
import com.sparta.spartaposts.repository.PostRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto creatPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);

        if (post.updateBeforeCheck()) {
            throw new NullPointerException();
        }
        Post savePost = postRepository.save(post);

        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).toList();
    }

    public void getPost(Long id) {
        findPost(id);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = findPost(id);

        if (Objects.equals(post.getPassword(), postRequestDto.getPassword())) {
            post.update(postRequestDto);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }

    public void deletePost(Long id, PostRequestDto postRequestDto) {
        Post post = findPost(id);

        if (Objects.equals(post.getPassword(), postRequestDto.getPassword())) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Post findPost(Long id) {

        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 포스트는 존재하지 않습니다."));
    }
}
