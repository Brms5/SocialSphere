package com.socialSphere.model.dto.Post;

import java.util.UUID;

import com.socialSphere.util.Enum.PostType;

public class PostCreateDto {
    
    private String description;

    private String image;

    private PostType type;

    public PostCreateDto(
        String description,
        String image,
        PostType type
    ) {
        this.description = description;
        this.image = image;
        this.type = type;
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
}
