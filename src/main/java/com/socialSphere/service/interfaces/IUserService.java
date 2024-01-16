package com.socialSphere.service.interfaces;

import java.util.UUID;

public interface IUserService {

    public void createNewFriendship(UUID userId, UUID friendId);

    public void removeFriendship(UUID userId, UUID friendId);

}
