package ru.job4j.socialmedia.dto;

public class PostUpdateDto {

    private long postId;
    private String title;
    private String description;

    public PostUpdateDto(long postId, String title, String description) {
        this.postId = postId;
        this.title = title;
        this.description = description;
    }

    public long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
