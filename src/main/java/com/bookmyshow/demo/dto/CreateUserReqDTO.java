package com.bookmyshow.demo.dto;

import lombok.Data;

@Data
public class CreateUserReqDTO implements IReqDTO {
    private String name;
    private String email;
    private String password;
}
