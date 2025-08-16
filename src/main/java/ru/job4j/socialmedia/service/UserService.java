package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    Optional<User> save(User user);

    Optional<UserDto> findById(long userId);

    boolean delete(long userId);

    boolean update(User user);
}