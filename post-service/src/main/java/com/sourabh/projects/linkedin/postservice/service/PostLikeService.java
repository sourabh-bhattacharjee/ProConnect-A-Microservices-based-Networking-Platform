package com.sourabh.projects.linkedin.postservice.service;

public interface PostLikeService {

    void likePost(Long postId, Long userId);

    void unlikePost(Long postId, Long userId);
}
