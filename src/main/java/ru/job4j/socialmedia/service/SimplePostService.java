package ru.job4j.socialmedia.service;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.PostDto;
import ru.job4j.socialmedia.dto.PostUpdateDto;
import ru.job4j.socialmedia.mapper.PostMapper;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

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
    public void createNewPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public boolean updatePost(PostUpdateDto postUpdateDto) {
        return postRepository.updateTitleAndDescription(
                postUpdateDto.getTitle(),
                postUpdateDto.getDescription(),
                postUpdateDto.getPostId()
        ) > 0;
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
    public Optional<PostDto> findById(long postId) {
        Optional<Post> foundPost = postRepository.findById(postId);
        if (foundPost.isPresent()) {
            return Optional.of(
                    postMapper.fromPostToPostDto(foundPost.get())
            );
        }
        return Optional.empty();
    }
}
