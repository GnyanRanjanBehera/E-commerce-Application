package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.dtos.AuthResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import jakarta.servlet.http.*;

import java.io.IOException;


public interface AuthService {

    public AuthResponse signUp(UserDto userDto);

    public AuthResponse signIn(String mobileNumber,String password);

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
