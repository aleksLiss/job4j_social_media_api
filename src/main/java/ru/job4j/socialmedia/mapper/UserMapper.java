package ru.job4j.socialmedia.mapper;

import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.model.User;

@Service
public class UserMapper {

    public User fromDtoToUser(UserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getLogin(),
                userDto.getPassword()
        );
    }

    public UserDto fromUserToDto(User user) {
        return new UserDto(
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
