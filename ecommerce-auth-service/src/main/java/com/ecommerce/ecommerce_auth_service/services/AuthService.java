package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;

public interface AuthService {

    public UserDto signUp(UserDto userDto);

    public UserDto signIn(String mobileNumber,String password);

}
