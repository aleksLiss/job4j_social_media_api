package ru.job4j.socialmedia.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Friendship;
import ru.job4j.socialmedia.model.Subscription;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeAll
    public void clearRepositories() {
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenDontSaveFriendshipAndFoundItByIdThenReturnEmptyOptional() {
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(1L);
        assertThat(foundSubscription).isEmpty();
    }

    @Test
    public void whenSaveFriendshipAndFoundItByIdThenReturnSavedFriendship() {
        User user = new User();
        user.setName("vova");
        user.setLogin("login");
        user.setPassword("psw");
        userRepository.save(user);
        Long userId = user.getId();
        Subscription savedSubscription = new Subscription();
        savedSubscription.setFollowingUserId(userId);
        subscriptionRepository.save(savedSubscription);
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(savedSubscription.getId());
        assertThat(foundSubscription).isPresent();
        assertThat(foundSubscription.get()).isEqualTo(savedSubscription);
    }

    @Test
    public void whenDontSavedSubscriptionsAndFoundAllThenReturnEmptyCollection() {
        List<Subscription> subscriptions = (List) subscriptionRepository.findAll();
        assertThat(subscriptions).isEmpty();
    }

    @Test
    public void whenSavedSubscriptionsAndFoundAllThenReturnCollectionWithSavedSubscriptions() {
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
        Subscription savedSubscription1 = new Subscription();
        Subscription savedSubscription2 = new Subscription();
        savedSubscription1.setFollowerUserId(user1Id);
        savedSubscription2.setFollowingUserId(user2Id);
        subscriptionRepository.save(savedSubscription1);
        subscriptionRepository.save(savedSubscription2);
        List<Subscription> subscriptions = (List) subscriptionRepository.findAll();
        assertThat(subscriptions)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(savedSubscription2, savedSubscription1);
    }

    @Test
    public void whenSavedSubscriptionAndDeleteItThenReturnEmptyOptional() {
        User user1 = new User();
        user1.setName("vova");
        user1.setLogin("login");
        user1.setPassword("psw");
        userRepository.save(user1);
        Long user1Id = user1.getId();
        Subscription savedSubscription = new Subscription();
        savedSubscription.setFollowingUserId(user1Id);
        subscriptionRepository.save(savedSubscription);
        subscriptionRepository.deleteById(savedSubscription.getId());
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(savedSubscription.getId());
        assertThat(foundSubscription).isEmpty();
    }

    @Test
    public void whenSavedSubscriptionsAndDeleteAllThenReturnEmptyCollection() {
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
        Subscription savedSubscription1 = new Subscription();
        Subscription savedSubscription2 = new Subscription();
        savedSubscription1.setFollowingUserId(user1Id);
        savedSubscription2.setFollowerUserId(user2Id);
        subscriptionRepository.save(savedSubscription1);
        subscriptionRepository.save(savedSubscription2);
        subscriptionRepository.deleteAll();
        List<Subscription> subscriptions = (List) subscriptionRepository.findAll();
        assertThat(subscriptions)
                .isEmpty();
    }
}