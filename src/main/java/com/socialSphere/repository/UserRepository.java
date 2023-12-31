package com.socialSphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialSphere.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {} 