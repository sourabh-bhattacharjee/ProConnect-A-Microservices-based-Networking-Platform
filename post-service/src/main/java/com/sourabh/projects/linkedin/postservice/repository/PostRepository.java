package com.sourabh.projects.linkedin.postservice.repository;

import com.sourabh.projects.linkedin.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
