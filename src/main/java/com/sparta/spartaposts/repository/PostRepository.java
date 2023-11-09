package com.sparta.spartaposts.repository;

import com.sparta.spartaposts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
