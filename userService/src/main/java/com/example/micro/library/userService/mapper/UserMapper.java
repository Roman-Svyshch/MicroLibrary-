package com.example.micro.library.userService.mapper;


import com.example.micro.library.userService.dto.UserDto;
import com.example.micro.library.userService.model.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto, User user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


}
