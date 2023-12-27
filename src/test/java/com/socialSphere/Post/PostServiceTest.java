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
        PostCreateDto postBody = PostFactory.createPostDto();
        User user = UserFactory.newUser();
        postBody.setUserId(user.getId());

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).then(returnsFirstArg());
        NewPostDto newPost = postService.createNewPost(postBody);

        //Assert
        Assertions.assertNotNull(newPost);
        Assertions.assertEquals(postBody.getDescription(), newPost.getDescription());
        Assertions.assertEquals(postBody.getImage(), newPost.getImage());
        Assertions.assertEquals(postBody.getType(), newPost.getType());
    }

    @Test
    public void createNewPostNullDescription() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        User user = UserFactory.newUser();
        postBody.setUserId(user.getId());
        postBody.setDescription(null);

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).then(returnsFirstArg());

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody);
        }, "Post fields cannot be null");
    }

    @Test
    public void createNewPostNullImage() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        User user = UserFactory.newUser();
        postBody.setUserId(user.getId());
        postBody.setImage(null);

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).then(returnsFirstArg());

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody);
        }, "Post fields cannot be null");
    }

    @Test
    public void createNewPostNullType() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        User user = UserFactory.newUser();
        postBody.setUserId(user.getId());
        postBody.setType(null);

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).then(returnsFirstArg());

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody);
        }, "Post fields cannot be null");
    }

    @Test
    public void createNewPostNullUserId() {
        //Arrange
        PostCreateDto postBody = PostFactory.createPostDto();
        postBody.setUserId(null);

        //Act
        Mockito.when(postRepository.save(Mockito.any(Post.class))).then(returnsFirstArg());

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postService.createNewPost(postBody);
        }, "Post fields cannot be null");
    }
}
