package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.dto.CreateUserReqDTO;
import com.bookmyshow.demo.dto.UserResponseDTO;
import com.bookmyshow.demo.mapper.UserMapper;
import com.bookmyshow.demo.models.User;
import com.bookmyshow.demo.service.UserServiceImpl;
import com.bookmyshow.demo.utils.UserReqValidationImpl;
import com.bookmyshow.demo.utils.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserReqValidationImpl userReqValidation;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserReqDTO createUserReqDTO) {
        ValidationResult validate = userReqValidation.validate(createUserReqDTO);
        if (!validate.isValid()) {
            return ResponseEntity.badRequest().body(validate.getErrorMessage());
        }
        User user = UserMapper.toUser(createUserReqDTO);
        User createdUser = userService.createUser(user);
        UserResponseDTO userResponseDTO = UserMapper.toUserResponseDTO(createdUser);

        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponseDTO userResponseDTO = UserMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }
}
