package ru.job4j.socialmedia.dto;

import java.util.Date;

public class PostDto {

    private long postId;
    private String title;
    private String description;
    private String pathToFile;
    private long userId;
    private Date created;

    public PostDto(long postId, String title, String description, String pathToFile, long userId, Date created) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.pathToFile = pathToFile;
        this.userId = userId;
        this.created = created;
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

    public String getPathToFile() {
        return pathToFile;
    }

    public long getUserId() {
        return userId;
    }

    public Date getCreated() {
        return created;
    }
}
