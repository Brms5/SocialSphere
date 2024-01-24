package com.socialSphere.controller;

import java.util.Optional;
import java.util.UUID;

import com.socialSphere.model.entity.Post;
import com.socialSphere.repository.PostRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.User;
import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<NewPostDto> createNewPost(@RequestBody @Valid PostCreateDto postCreateDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID userId = user.getId();

        return ResponseEntity.status(Response.SC_CREATED).body(postService.createNewPost(postCreateDto, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewPostDto> getPostById(@PathVariable("id") UUID id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        NewPostDto newPost = new NewPostDto(
            post.get().getDescription(),
            post.get().getImage(),
            post.get().getDate(),
            post.get().getType()
        );

        return ResponseEntity.ok(newPost);
    }
    
}
