package com.sourabh.projects.linkedin.postservice.service;

import com.sourabh.projects.linkedin.postservice.dto.PostCreateRequestDto;
import com.sourabh.projects.linkedin.postservice.dto.PostDto;
import com.sourabh.projects.linkedin.postservice.entity.Post;
import com.sourabh.projects.linkedin.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {

        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {

        Optional<Post> post = postRepository.findById(postId);
        return post == null ?  null : modelMapper.map(post, PostDto.class);

    }

    @Override
    public List<PostDto> getAllPostOfUser(Long userId) {
        List<Post> posts =  postRepository.findByUserId(userId);
        return posts.stream()
                .map((element) -> modelMapper.map(element,PostDto.class))
                .toList();

    }
}
