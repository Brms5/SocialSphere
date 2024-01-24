package com.socialSphere.service.interfaces;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;

import java.util.UUID;

public interface IPostService {
    
    public NewPostDto createNewPost(PostCreateDto postBodyDto, UUID userId) throws ClassNotFoundException;

}
