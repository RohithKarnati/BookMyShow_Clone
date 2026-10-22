package com.bookmyshow.demo.utils;

import com.bookmyshow.demo.dto.CreateUserReqDTO;
import com.bookmyshow.demo.dto.IReqDTO;

public interface IRequestValidator<T extends IReqDTO>  {

    ValidationResult validate(T IReqDTO);

}
