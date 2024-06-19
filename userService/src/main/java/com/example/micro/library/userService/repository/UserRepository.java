package com.example.micro.library.userService.repository;

import com.example.micro.library.userService.dto.UserDto;
import com.example.micro.library.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);
//    User findById(long id);
}
