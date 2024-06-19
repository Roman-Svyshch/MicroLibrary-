package com.example.micro.library.userService.controller;

import com.example.micro.library.userService.constants.UserConstants;
import com.example.micro.library.userService.dto.ResponseDto;
import com.example.micro.library.userService.dto.UserContactInfoDto;
import com.example.micro.library.userService.dto.UserDto;
import com.example.micro.library.userService.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserServices iUserServices;
    @Autowired
    private UserContactInfoDto userContactInfoDto;
    public UserController(IUserServices iUserServices) {
        this.iUserServices = iUserServices;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createNewUser(@RequestBody UserDto userDto){
        iUserServices.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> getUserInfo(@RequestParam long id){
        UserDto userDto = iUserServices.getUserInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password){
        boolean loggedIn = iUserServices.login(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(loggedIn);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestParam long id, @RequestBody UserDto userDto){
        boolean isUpdated = iUserServices.updateUser(id, userDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(UserConstants.STATUS_404, "User not found"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteById(@RequestParam long id){
        boolean isDeleted = iUserServices.deleteUserById(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(UserConstants.STATUS_404, "User not found"));
        }
    }

    @GetMapping("/contact-info")
    public ResponseEntity<UserContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userContactInfoDto);
    }
}
