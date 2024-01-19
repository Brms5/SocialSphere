package com.socialSphere.service;

import java.time.LocalDate;

import com.socialSphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.Post;
import com.socialSphere.repository.PostRepository;
import com.socialSphere.service.interfaces.IPostService;
import com.socialSphere.util.Validate.Validator;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    private UserRepository userRepository;

    @Transactional
    public NewPostDto createNewPost(PostCreateDto postCreateDto) {
        var hasNullField = Validator.hasNullField(postCreateDto);
        if (hasNullField) {
            throw new IllegalArgumentException("Post fields cannot be null");
        }

        Post post = new Post(
                postCreateDto.getDescription(),
                postCreateDto.getImage(),
                LocalDate.now(),
                postCreateDto.getType(),
                postCreateDto.getUserId()
        );

        Post newPost = postRepository.save(post);
        return new NewPostDto(newPost.getDescription(), newPost.getImage(), newPost.getDate(), newPost.getType());
    }
}
