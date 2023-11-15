package com.socialSphere.service.interfaces;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;

public interface IPostService {
    
    public NewPostDto createNewPost(PostCreateDto postBodyDto);

}
