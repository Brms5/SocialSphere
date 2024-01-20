package com.socialSphere.Post;

import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.Post;
import com.socialSphere.util.Enum.PostType;

import java.time.LocalDate;
import java.util.UUID;

public class PostFactory {
    public static PostCreateDto createPostDto() {

        return new PostCreateDto(
                "desciption",
                "image.jpg",
                PostType.NORMAL
        );
    }

    public static NewPostDto createNewPostDto() {

        return new NewPostDto(
                "desciption",
                "image.jpg",
                LocalDate.now(),
                PostType.NORMAL
        );
    }

    public static Post createPost() {
        return new Post(
                "desciption",
                "image.jpg",
                LocalDate.now(),
                PostType.NORMAL,
                UUID.randomUUID()
        );
    }
}
