package ru.job4j.socialmedia.service;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.PostSaveDto;
import ru.job4j.socialmedia.mapper.PostMapper;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SimplePostService implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public SimplePostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Post savePost(PostSaveDto postSaveDto) {
        return postRepository.save(
                new Post(
                        postSaveDto.getTitle(),
                        postSaveDto.getDescription(),
                        postSaveDto.getPathToFile(),
                        postSaveDto.getUserId(),
                        Timestamp.valueOf(LocalDateTime.now())
                )
        );
    }

    @Override
    public boolean updatePost(Post post) {
        Optional<Post> foundPost = postRepository.findById(post.getId());
        if (foundPost.isPresent()) {
            return postRepository.updateTitleAndDescription(
                    post.getTitle(),
                    post.getDescription(),
                    post.getId()
            ) > 0;
        }
        return false;
    }

    @Override
    public boolean deletePost(long postId) {
        return postRepository.deletePost(postId) > 0;
    }

    @Override
    public List<Post> findAllByUserId(long userId) {
        return postRepository.findByUserId(userId).stream().toList();
    }

    @Override
    public Optional<PostSaveDto> findById(long postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isPresent()) {
            return Optional.of(
                new PostSaveDto(
                        foundPost.get().getTitle(),
                        foundPost.get().getDescription(),
                        foundPost.get().getUserId(),
                        foundPost.get().getPathToFile()
                )
            );
        }
        return Optional.empty();
    }
}
