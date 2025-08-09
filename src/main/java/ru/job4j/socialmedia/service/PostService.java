package ru.job4j.socialmedia.service;

public interface PostService {

    void createNewPost(String title, String description, String pathToFile, long userId);

    void updatePost(String title, String description, long postId);

    void deletePost(long postId);
}
