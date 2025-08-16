package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.PostDto;
import ru.job4j.socialmedia.dto.PostUpdateDto;
import ru.job4j.socialmedia.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    void createNewPost(Post post);

    boolean updatePost(PostUpdateDto postUpdateDto);

    boolean deletePost(long postId);

    List<Post> findAllByUserId(long userId);

    Optional<PostDto> findById(long postId);
}
