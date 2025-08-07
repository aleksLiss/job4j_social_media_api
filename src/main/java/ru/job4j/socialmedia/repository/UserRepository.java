package ru.job4j.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT * FROM Users WHERE login = :userLogin AND password = :userPassword
            """, nativeQuery = true)
    Optional<User> findByLoginAndPassword(@Param("userLogin") String login,
                                          @Param("userPassword") String password);

    @Query(value = """
            SELECT * FROM Followers WHERE following_user_id = :userId
            """, nativeQuery = true)
    Collection<User> getAllSubscribers(@Param("userId") long userId);

    @Query(value = """
            SELECT * FROM Friends WHERE from_user_id = :userId
            """, nativeQuery = true)
    Collection<User> getAllFriends(@Param("userId") long userId);

    @Query(value = """
            SELECT * FROM posts WHERE user_id
            IN (SELECT follower_user_id FROM Followers WHERE following_user_id = :userId)
            """, nativeQuery = true)
    Page<Post> findByOrderByCreatedDesc(@Param("userId") long userId, Pageable pageable);
}
