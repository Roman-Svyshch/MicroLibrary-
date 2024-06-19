package com.example.micro.library.userService.services.serviceImpl;

import com.example.micro.library.userService.dto.UserDto;
import com.example.micro.library.userService.exception.NotCorrectInputDataException;
import com.example.micro.library.userService.exception.UserAlreadyExistException;
import com.example.micro.library.userService.exception.UserNotFoundException;
import com.example.micro.library.userService.mapper.UserMapper;
import com.example.micro.library.userService.model.User;
import com.example.micro.library.userService.repository.UserRepository;
import com.example.micro.library.userService.services.IUserServices;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserServices {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistException("User with username " + userDto.getUsername() + " already exists. Please choose another username.");
        }
        User newUser = UserMapper.mapToUser(userDto, new User());
        return userRepository.save(newUser);
    }

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
        } else {
            throw new NotCorrectInputDataException("Username or password not correct, please try again");
        }
    }

    @Override
    public UserDto getUserInfo(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserMapper.mapToUserDto(user, new UserDto());
    }

    @Override
    public boolean deleteUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean updateUser(long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(userDto.getPassword());
            userRepository.save(existingUser);
            return true;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }
}
