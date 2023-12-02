package com.socialSphere.model.dto.Post;

import java.time.LocalDate;

import com.socialSphere.util.Enum.PostType;

public class NewPostDto {

    private String description;

    private String image;

    private LocalDate date;

    private PostType type;

    public NewPostDto(
        String description,
        String image,
        LocalDate date,
        PostType type
    ) {
        this.description = description;
        this.image = image;
        this.date = date;
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public PostType getType() {
        return this.type;
    }

    public void setType(PostType type) {
        this.type = type;
    }
    
}
