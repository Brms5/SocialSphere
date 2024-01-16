package com.socialSphere.model.dto.Authentication;

import com.socialSphere.util.Enum.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
