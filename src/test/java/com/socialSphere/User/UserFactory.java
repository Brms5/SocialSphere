package com.socialSphere.User;

import com.socialSphere.model.entity.User;
import com.socialSphere.util.Enum.UserRole;

import java.util.ArrayList;
import java.util.UUID;

public class UserFactory {
    public static User newUser() {
        return new User(
                UUID.randomUUID(),
                "Test",
                "test@email.com",
                "test123",
                UserRole.USER,
                new ArrayList<User>()
        );
    }
}
