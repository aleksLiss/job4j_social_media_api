package ru.job4j.socialmedia.dto;

import ru.job4j.socialmedia.model.Post;

import java.util.List;

public class UserPostsDto {

    private Long userId;
    private String username;
    private List<Post> posts;

    public UserPostsDto(Long userId, String username, List<Post> posts) {
        this.userId = userId;
        this.username = username;
        this.posts = posts;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
