package com.example.micro.library.userService.services;

import com.example.micro.library.userService.dto.UserDto;
import com.example.micro.library.userService.model.User;

public interface IUserServices {

    User createUser (UserDto userDto);
    boolean login (String userName,String password);
    UserDto getUserInfo(long id);
    boolean updateUser(long id , UserDto userDto);
    boolean deleteUserById(long id);
}
