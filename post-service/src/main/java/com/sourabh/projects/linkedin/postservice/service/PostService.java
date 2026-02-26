package com.sourabh.projects.linkedin.postservice.service;

import com.sourabh.projects.linkedin.postservice.dto.PostCreateRequestDto;
import com.sourabh.projects.linkedin.postservice.dto.PostDto;

import java.util.List;


public interface PostService {

    PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostOfUser(Long userId);
}
