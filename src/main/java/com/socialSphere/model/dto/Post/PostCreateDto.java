package com.socialSphere.model.dto.Post;

import java.util.UUID;

import com.socialSphere.util.Enum.PostType;

public class PostCreateDto {
    
    private String description;

    private String image;

    private PostType type;

    private UUID userId;

    public PostCreateDto(
        String description,
        String image,
        PostType type,
        UUID userId
    ) {
        this.description = description;
        this.image = image;
        this.type = type;
        this.userId = userId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PostType getType() {
        return this.type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
