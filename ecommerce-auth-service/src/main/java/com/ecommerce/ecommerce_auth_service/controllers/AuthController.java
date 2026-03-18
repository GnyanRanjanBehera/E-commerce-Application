package com.ecommerce.ecommerce_auth_service.controllers;


import com.ecommerce.ecommerce_auth_service.domains.dtos.AuthResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.services.AuthService;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    final private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserDto userDto){
        AuthResponse authResponse = authService.signUp(userDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(
            @RequestParam(value = "mobileNumber") String mobileNUmber,
            @RequestParam(value = "password") String password
            ){
        AuthResponse authResponse = authService.signIn(mobileNUmber, password);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);

    }

    @PostMapping("/refreshToken")
    public void refreshToken( HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        authService.refreshToken(request,response);
    }


}
