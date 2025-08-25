package ru.job4j.socialmedia.dto;

import jakarta.validation.constraints.NotBlank;

public class PostSaveDto {

    @NotBlank(message = "заголовок не может быть пустым")
    private String title;
    @NotBlank(message = "описание не может быть пустым")
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
