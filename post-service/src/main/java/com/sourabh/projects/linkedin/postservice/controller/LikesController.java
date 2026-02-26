package com.sourabh.projects.linkedin.postservice.controller;

import com.sourabh.projects.linkedin.postservice.service.PostLikeService;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(postId,1L);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> UnlikePost(@PathVariable Long postId){
        postLikeService.unlikePost(postId,1L);
        return ResponseEntity.noContent().build();
    }
}
