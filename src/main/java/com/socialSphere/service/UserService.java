package com.socialSphere.service;

import com.socialSphere.model.entity.User;
import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public void createNewFriendship(UUID userId, UUID friendId) {
        User user = userRepository.findById(userId).get();
        User friend = userRepository.findById(friendId).get();
        user.getFriends().add(friend);
        friend.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(friend);
    }

    public void removeFriendship(UUID userId, UUID friendId) {
        User user = userRepository.findById(userId).get();
        User friend = userRepository.findById(friendId).get();
        user.getFriends().remove(friend);
        friend.getFriends().remove(user);
        userRepository.save(user);
        userRepository.save(friend);
    }
}
