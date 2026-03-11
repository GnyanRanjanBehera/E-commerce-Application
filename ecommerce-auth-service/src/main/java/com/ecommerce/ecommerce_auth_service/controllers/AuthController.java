package com.ecommerce.ecommerce_auth_service.controllers;


import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    final private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto){
        UserDto user = authService.signUp(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(
            @RequestParam(value = "mobileNumber") String mobileNUmber,
            @RequestParam(value = "password") String password
            ){
        UserDto userDto = authService.signIn(mobileNUmber, password);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


}
