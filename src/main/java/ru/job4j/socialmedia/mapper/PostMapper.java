package ru.job4j.socialmedia.mapper;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.PostSaveDto;
import ru.job4j.socialmedia.dto.UserPostsDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.PostRepository;
import ru.job4j.socialmedia.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostMapper {

    public Optional<UserPostsDto> fromPostToUserPostsDto(Long userId, UserRepository userRepository, PostRepository postRepository) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            return Optional.of(new UserPostsDto(
                    userId,
                    userRepository.findById(userId).get().getName(),
                    postRepository.findByUserId(userId).stream().toList()
            ));
        }
        return Optional.empty();
    }
}