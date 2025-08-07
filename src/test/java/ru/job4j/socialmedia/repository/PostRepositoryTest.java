package ru.job4j.socialmedia.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Post;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
    }

    @BeforeAll
    public void clearRepositories() {
        postRepository.deleteAll();
    }

    @Test
    public void whenDontSavePostAndFoundItByIdThenReturnEmptyOptional() {
        Optional<Post> foundPost = postRepository.findById(1L);
        assertThat(foundPost).isEmpty();
    }

    @Test
    public void whenSavePostAndFoundItByIdThenReturnSavedPost() {
        Post savedPost = new Post();
        savedPost.setTitle("title");
        savedPost.setDescription("descr");
        postRepository.save(savedPost);
        Optional<Post> foundPost = postRepository.findById(savedPost.getId());
        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getTitle()).isEqualTo("title");
    }

    @Test
    public void whenDontSavedPostsAndFoundAllThenReturnEmptyCollection() {
        List<Post> posts = (List) postRepository.findAll();
        assertThat(posts).isEmpty();
    }

    @Test
    public void whenSavedPostsAndFoundAllThenReturnCollectionWithSavedPosts() {
        Post savedPost1 = new Post();
        savedPost1.setTitle("title1");
        savedPost1.setDescription("descr1");
        Post savedPost2 = new Post();
        savedPost2.setTitle("title2");
        savedPost2.setDescription("descr2");
        postRepository.save(savedPost1);
        postRepository.save(savedPost2);
        List<Post> posts = (List) postRepository.findAll();
        assertThat(posts)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(savedPost1, savedPost2);
    }

    @Test
    public void whenSavedPostAndDeleteItThenReturnEmptyOptional() {
        Post savedPost1 = new Post();
        savedPost1.setTitle("title");
        savedPost1.setDescription("descr");
        postRepository.save(savedPost1);
        postRepository.deleteById(savedPost1.getId());
        Optional<Post> foundPost = postRepository.findById(savedPost1.getId());
        assertThat(foundPost).isEmpty();
    }

    @Test
    public void whenSavedPostsAndDeleteAllThenReturnEmptyCollection() {
        Post savedPost1 = new Post();
        savedPost1.setTitle("title");
        savedPost1.setDescription("descr");
        Post savedPost2 = new Post();
        savedPost2.setTitle("title");
        savedPost2.setDescription("descr");
        postRepository.save(savedPost1);
        postRepository.save(savedPost2);
        postRepository.deleteAll();
        List<Post> posts = (List) postRepository.findAll();
        assertThat(posts)
                .isEmpty();
    }
}