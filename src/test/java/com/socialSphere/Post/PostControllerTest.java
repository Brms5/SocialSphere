package com.socialSphere.Post;

import com.socialSphere.User.UserFactory;
import com.socialSphere.controller.PostController;
import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.User;
import com.socialSphere.repository.UserRepository;
import com.socialSphere.service.PostService;
import org.apache.catalina.connector.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostService postService;

    @Test
    public void createNewPostSuccessfully() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        User user = UserFactory.newUser();
        postBody.setUserId(user.getId());

        NewPostDto createdPost = PostFactory.createNewPostDto();
        ResponseEntity<NewPostDto> expectedResponse = ResponseEntity
                .status(Response.SC_CREATED)
                .body(createdPost);

        //Action
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(postService.createNewPost(postBody)).thenReturn(createdPost);
        ResponseEntity<NewPostDto> postResponse = postController.createNewPost(postBody);

        //Assert
        Assertions.assertEquals(postBody.getUserId(), user.getId());
        Assertions.assertEquals(expectedResponse, postResponse);
    }

    @Test
    public void createNewPostUserNotFound() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        ResponseEntity<NewPostDto> expectedResponse = ResponseEntity
                .status(Response.SC_NOT_FOUND)
                .build();

        //Action
        ResponseEntity<NewPostDto> postResponse = postController.createNewPost(postBody);

        //Assert
        Assertions.assertEquals(expectedResponse, postResponse);
    }
}
