package ru.job4j.socialmedia.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @BeforeAll
    public void clearRepositories() {
        userRepository.deleteAll();
    }

    @Test
    public void whenDontSaveUserAndFoundItByIdThenReturnEmptyOptional() {
        Optional<User> foundUser = userRepository.findById(1L);
        assertThat(foundUser).isEmpty();
    }

    @Test
    public void whenSaveUserAndFoundItByIdThenReturnSavedUser() {
        User savedUser = new User();
        savedUser.setLogin("login");
        savedUser.setName("vova");
        savedUser.setPassword("psw");
        userRepository.save(savedUser);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getLogin()).isEqualTo("login");
    }

    @Test
    public void whenDontSavedUserAndFoundAllThenReturnEmptyCollection() {
        List<User> users = (List) userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    public void whenSavedUserAndFoundAllThenReturnCollectionWithSavedUsers() {
        User savedUser1 = new User();
        savedUser1.setLogin("login1");
        savedUser1.setName("vova");
        savedUser1.setPassword("psw");
        User savedUser2 = new User();
        savedUser2.setLogin("login2");
        savedUser2.setName("vova");
        savedUser2.setPassword("psw");
        userRepository.save(savedUser1);
        userRepository.save(savedUser2);
        List<User> users = (List) userRepository.findAll();
        assertThat(users)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(savedUser1, savedUser2);
    }

    @Test
    public void whenSavedUserAndDeleteItThenReturnEmptyOptional() {
        User savedUser1 = new User();
        savedUser1.setLogin("login1");
        savedUser1.setName("vova");
        savedUser1.setPassword("psw");
        userRepository.save(savedUser1);
        userRepository.deleteById(savedUser1.getId());
        Optional<User> foundUser = userRepository.findById(savedUser1.getId());
        assertThat(foundUser).isEmpty();
    }

    @Test
    public void whenSavedUsersAndDeleteAllThenReturnEmptyCollection() {
        User savedUser1 = new User();
        savedUser1.setLogin("login1");
        savedUser1.setName("vova");
        savedUser1.setPassword("psw");
        User savedUser2 = new User();
        savedUser2.setLogin("login2");
        savedUser2.setName("vova");
        savedUser2.setPassword("psw");
        userRepository.save(savedUser1);
        userRepository.save(savedUser2);
        userRepository.deleteAll();
        List<User> users = (List) userRepository.findAll();
        assertThat(users)
                .isEmpty();
    }
}

