package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.PostSaveDto;
import ru.job4j.socialmedia.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post savePost(PostSaveDto postSaveDto);

    boolean updatePost(Post post);

    boolean deletePost(long postId);

    List<Post> findAllByUserId(long userId);

    Optional<PostSaveDto> findById(long postId);
}
