package com.socialSphere.Post;

import com.socialSphere.User.UserFactory;
import com.socialSphere.model.dto.Post.NewPostDto;
import com.socialSphere.model.dto.Post.PostCreateDto;
import com.socialSphere.model.entity.Post;
import com.socialSphere.model.entity.User;
import com.socialSphere.repository.PostRepository;
import com.socialSphere.service.PostService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Test
    public void createNewPostSuccessfully() {
        //Arrange
        User user = UserFactory.newUser();
        PostCreateDto postBody = PostFactory.createPostDto();

        Post savedPost = PostFactory.createPost();
        savedPost.setUserId(user.getId());

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(savedPost);
        NewPostDto newPost = postService.createNewPost(postBody, user.getId());

        //Assert
        Assertions.assertNotNull(newPost);

        Assertions.assertEquals(postBody.getDescription(), savedPost.getDescription());
        Assertions.assertEquals(postBody.getImage(), newPost.getImage());
        Assertions.assertEquals(postBody.getType(), newPost.getType());

        Assertions.assertEquals(savedPost.getDescription(), postBody.getDescription());
        Assertions.assertEquals(savedPost.getImage(), postBody.getImage());
        Assertions.assertEquals(savedPost.getType(), postBody.getType());

        Assertions.assertEquals(savedPost.getDescription(), newPost.getDescription());
        Assertions.assertEquals(savedPost.getImage(), newPost.getImage());
        Assertions.assertEquals(savedPost.getType(), newPost.getType());
    }

    @Test
    public void createNewPostNullDescription() {
        //Arrange
        User user = UserFactory.newUser();

        PostCreateDto postBody = PostFactory.createPostDto();
        postBody.setDescription(null);

        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody, user.getId());
        }, "Post fields cannot be null");
    }

    @Test
    public void createNewPostNullImage() {
        //Arrange
        User user = UserFactory.newUser();

        PostCreateDto postBody = PostFactory.createPostDto();
        postBody.setImage(null);

        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody, user.getId());
        }, "Post fields cannot be null");
    }

    @Test
    public void createNewPostNullType() {
        //Arrange
        User user = UserFactory.newUser();

        PostCreateDto postBody = PostFactory.createPostDto();
        postBody.setType(null);

        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody, user.getId());
        }, "Post fields cannot be null");
    }
}
