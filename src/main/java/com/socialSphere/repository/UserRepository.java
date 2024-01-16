package com.socialSphere.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialSphere.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);
}