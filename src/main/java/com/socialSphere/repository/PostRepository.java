package com.socialSphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialSphere.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> { }
