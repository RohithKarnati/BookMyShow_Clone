package com.bookmyshow.demo.mapper;

import com.bookmyshow.demo.dto.CreateUserReqDTO;
import com.bookmyshow.demo.dto.UserResponseDTO;
import com.bookmyshow.demo.models.User;

public class UserMapper {

    public static User toUser(CreateUserReqDTO createUserReqDTO) {
        User user = new User();
        user.setName(createUserReqDTO.getName());
        user.setEmail(createUserReqDTO.getEmail());
        user.setPassword(createUserReqDTO.getPassword());


        return user;
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setTickets(user.getTickets());
        return userResponseDTO;
    }
}
