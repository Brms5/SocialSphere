package com.socialSphere.controller;

import com.socialSphere.model.entity.User;
import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/friendship/{id}")
    public ResponseEntity<String> createNewFriendship(@PathVariable("id") UUID friendId) {
        User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userLogged.getId());
        Optional<User> friend = userRepository.findById(friendId);

        if (user.isEmpty() || friend.isEmpty()) {
            return ResponseEntity.badRequest().body("User or friend doesn't exist!");
        }

        if (user.get().getId().equals(friend.get().getId())) {
            return ResponseEntity.badRequest().body("You can't be friends with yourself!");
        }

        Optional<User> friendAlreadyAdded = user.get().getFriends().stream().filter(u -> u.getId().equals(friendId)).findFirst();

        if (friendAlreadyAdded.isPresent()) {
            return ResponseEntity.badRequest().body("You are already friends!");
        }

        userService.createNewFriendship(user.get().getId(), friend.get().getId());
        return ResponseEntity.ok("Friendship created!");
    }

    @DeleteMapping("/friendship/{id}")
    public ResponseEntity<String> removeFriendship(@PathVariable("id") UUID friendId) {
        User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userLogged.getId());

        Optional<User> friend = user.get().getFriends().stream().filter(u -> u.getId().equals(friendId)).findFirst();

        if (user.isEmpty() || friend.isEmpty()) {
            return ResponseEntity.badRequest().body("User or friend doesn't exist!");
        }

        if (user.get().getId().equals(friend.get().getId())) {
            return ResponseEntity.badRequest().body("You can't be friends with yourself!");
        }

        userService.removeFriendship(user.get().getId(), friend.get().getId());
        return ResponseEntity.ok("Friendship removed!"); }
}
