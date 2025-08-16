package ru.job4j.socialmedia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message = "имя не должно быть пустым")
    private String name;
    @Email(message = "логин должен быть уникальным")
    private String login;
    @NotBlank(message = "пароль не должен быть пустым")
    private String password;

    public UserDto(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
