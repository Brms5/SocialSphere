package com.socialSphere.service;

import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

}
