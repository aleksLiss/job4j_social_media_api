package ru.job4j.socialmedia.mapper;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.PostDto;
import ru.job4j.socialmedia.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PostMapper {

    public PostDto fromPostToPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getPathToFile(),
                post.getUserId(),
                post.getCreated()
        );
    }

    public Post fromPostDtoToPost(PostDto postDto) {
        return new Post(
                postDto.getTitle(),
                postDto.getDescription(),
                postDto.getPathToFile(),
                postDto.getUserId(),
                Timestamp.valueOf(LocalDateTime.now())
        );
    }
}
