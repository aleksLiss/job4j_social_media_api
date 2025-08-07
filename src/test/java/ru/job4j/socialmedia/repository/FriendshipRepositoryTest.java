package ru.job4j.socialmedia.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Friendship;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FriendshipRepositoryTest {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        friendshipRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeAll
    public void clearRepositories() {
        friendshipRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenDontSaveFriendshipAndFoundItByIdThenReturnEmptyOptional() {
        Optional<Friendship> foundFriendship = friendshipRepository.findById(1L);
        assertThat(foundFriendship).isEmpty();
    }

    @Test
    public void whenSaveFriendshipAndFoundItByIdThenReturnSavedFriendship() {
        User user = new User();
        user.setName("vova");
        user.setLogin("login");
        user.setPassword("psw");
        userRepository.save(user);
        Long userId = user.getId();
        Friendship savedFriendship = new Friendship();
        savedFriendship.setFromUserId(userId);
        friendshipRepository.save(savedFriendship);
        Optional<Friendship> foundFriendship = friendshipRepository.findById(savedFriendship.getId());
        assertThat(foundFriendship).isPresent();
        assertThat(foundFriendship.get()).isEqualTo(savedFriendship);
    }

    @Test
    public void whenDontSavedFriendshipsAndFoundAllThenReturnEmptyCollection() {
        List<Friendship> friendships = (List) friendshipRepository.findAll();
        assertThat(friendships).isEmpty();
    }

    @Test
    public void whenSavedFriendshipsAndFoundAllThenReturnCollectionWithSavedFriendships() {
        User user1 = new User();
        User user2 = new User();
        user1.setName("vova");
        user1.setLogin("login");
        user1.setPassword("psw");
        user2.setName("misha");
        user2.setLogin("mishin_login");
        user2.setPassword("psw");
        userRepository.save(user1);
        userRepository.save(user2);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        Friendship savedFriendship1 = new Friendship();
        Friendship savedFriendship2 = new Friendship();
        savedFriendship1.setFromUserId(user1Id);
        savedFriendship2.setFromUserId(user2Id);
        friendshipRepository.save(savedFriendship1);
        friendshipRepository.save(savedFriendship2);
        List<Friendship> friendships = (List) friendshipRepository.findAll();
        assertThat(friendships)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(savedFriendship1, savedFriendship2);
    }

    @Test
    public void whenSavedFriendshipAndDeleteItThenReturnEmptyOptional() {
        User user1 = new User();
        user1.setName("vova");
        user1.setLogin("login");
        user1.setPassword("psw");
        userRepository.save(user1);
        Long user1Id = user1.getId();
        Friendship savedFriendship = new Friendship();
        savedFriendship.setFromUserId(user1Id);
        friendshipRepository.save(savedFriendship);
        friendshipRepository.deleteById(savedFriendship.getId());
        Optional<Friendship> foundFriendship = friendshipRepository.findById(savedFriendship.getId());
        assertThat(foundFriendship).isEmpty();
    }

    @Test
    public void whenSavedFriendshipsAndDeleteAllThenReturnEmptyCollection() {
        User user1 = new User();
        User user2 = new User();
        user1.setName("vova");
        user1.setLogin("login");
        user1.setPassword("psw");
        user2.setName("misha");
        user2.setLogin("mishin_login");
        user2.setPassword("psw");
        userRepository.save(user1);
        userRepository.save(user2);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        Friendship savedFriendship1 = new Friendship();
        Friendship savedFriendship2 = new Friendship();
        savedFriendship1.setFromUserId(user1Id);
        savedFriendship2.setFromUserId(user2Id);
        friendshipRepository.save(savedFriendship1);
        friendshipRepository.save(savedFriendship2);
        friendshipRepository.deleteAll();
        List<Friendship> friendships = (List) friendshipRepository.findAll();
        assertThat(friendships)
                .isEmpty();
    }
}