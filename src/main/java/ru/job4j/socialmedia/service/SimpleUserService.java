package ru.job4j.socialmedia.service;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.mapper.UserMapper;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public SimpleUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> save(User user) {
        Optional<User> foundUser = userRepository.findByLoginAndPassword(
                user.getLogin(),
                user.getPassword()
        );
        if (foundUser.isEmpty()) {
            return Optional.of(userRepository.save(user));

        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> findById(long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            return Optional.ofNullable(userMapper.fromUserToDto(
                    foundUser.get()
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long userId) {
        return userRepository.deleteById(userId) > 0;
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getPassword()
        ) > 0;
    }
}
