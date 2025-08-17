package ru.job4j.socialmedia.dto;

public class PostSaveDto {

    private String title;
    private String description;
    private long userId;
    private String pathToFile;

    public PostSaveDto(String title, String description, long userId, String pathToFile) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
