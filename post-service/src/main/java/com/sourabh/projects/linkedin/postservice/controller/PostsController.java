package com.sourabh.projects.linkedin.postservice.controller;

import com.sourabh.projects.linkedin.postservice.dto.PostCreateRequestDto;
import com.sourabh.projects.linkedin.postservice.dto.PostDto;
import com.sourabh.projects.linkedin.postservice.exception.BadRequestException;
import com.sourabh.projects.linkedin.postservice.exception.ResourceNotFoundException;
import com.sourabh.projects.linkedin.postservice.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        PostDto createdPost = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = postService.getPostById(postId);
        if(postDto == null){
            throw new ResourceNotFoundException("Post not found with id: "+postId);
        }
        return ResponseEntity.ok(postDto);
    }
    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDto> post = postService.getAllPostOfUser(userId);
        if(post.isEmpty()) throw new BadRequestException("User with id: "+ userId + " do not have any posts");
        return ResponseEntity.ok(post);
    }

}
