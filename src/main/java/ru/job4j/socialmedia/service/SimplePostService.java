package ru.job4j.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SimplePostService implements PostService {

    private final PostRepository postRepository;

    public SimplePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createNewPost(String title, String description, String pathToFile, long userId) {
        Post createdPost = new Post(
                title,
                description,
                pathToFile,
                userId,
                Timestamp.valueOf(LocalDateTime.now())
        );
        postRepository.save(createdPost);
    }

    @Override
    public void updatePost(String title, String description, long postId) {
        postRepository.updateTitleAndDescription(title, description, postId);
    }

    @Override
    public void deletePost(long postId) {
        postRepository.deletePost(postId);
    }
}
