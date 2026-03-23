package com.ecommerce.ecommerce_auth_service.controllers;


import com.ecommerce.ecommerce_auth_service.domains.dtos.ApiResponseMessage;
import com.ecommerce.ecommerce_auth_service.domains.dtos.CursorPageResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById(){
        return null;
    }

    @GetMapping("/getUserByMobileNumber")
    public ResponseEntity<UserDto> getUserByMobileNumber(){
        return null;
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserDto> getUserByEmail(){
        return null;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<CursorPageResponse<UserDto>> getAllUsers(
            @RequestParam(value = "cursor",defaultValue = "0") Long cursor,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sortDir",defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "name") String sortBy
    ){
        CursorPageResponse<UserDto> allUsers = userService.getAllUsers(cursor, size, sortBy, sortDir);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(){
        return null;
    }

    public ResponseEntity<ApiResponseMessage> deleteUser(){
        return null;
    }

}
