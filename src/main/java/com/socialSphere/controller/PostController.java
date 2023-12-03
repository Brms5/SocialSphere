package com.socialSphere.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.User;
import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    private UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<NewPostDto> createNewPost(@RequestBody @Valid PostCreateDto postCreateDto) {
        Optional<User> user = userRepository.findById(postCreateDto.getUserId());
        if (user.isEmpty()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        postCreateDto.setUserId(user.get().getId());
        return ResponseEntity.status(Response.SC_CREATED).body(postService.createNewPost(postCreateDto));
    }
    
}
