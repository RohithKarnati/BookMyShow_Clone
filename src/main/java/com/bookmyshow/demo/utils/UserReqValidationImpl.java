package com.bookmyshow.demo.utils;

import com.bookmyshow.demo.dto.CreateUserReqDTO;
import com.bookmyshow.demo.dto.IReqDTO;
import org.springframework.stereotype.Component;

@Component
public class UserReqValidationImpl implements IRequestValidator<CreateUserReqDTO> {

    @Override
    public ValidationResult validate(CreateUserReqDTO createUserReqDTO) {

        createUserReqDTO.getEmail();
        createUserReqDTO.getName();
        createUserReqDTO.getPassword();

        if (createUserReqDTO.getEmail() == null || createUserReqDTO.getEmail().isEmpty()) {
            return new ValidationResult(false, "Email is required");
        }
        // validate email format
        if (!createUserReqDTO.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return new ValidationResult(false, "Invalid email format");
        }

        if (createUserReqDTO.getName() == null || createUserReqDTO.getName().isEmpty()) {
            return new ValidationResult(false, "Name is required");
        }

        if (createUserReqDTO.getPassword() == null || createUserReqDTO.getPassword().isEmpty()) {
            return new ValidationResult(false, "Password is required");
        }
        //validate password - it must contain small and capital and number and special character and length must be 8
        if (!createUserReqDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return new ValidationResult(false, "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character and must be at least 8 characters long");
        }

        return new ValidationResult(true, null); // Return true if valid, false otherwise
    }

}
