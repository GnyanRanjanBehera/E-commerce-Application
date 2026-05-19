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
    public ResponseEntity<UserDto> getUserById(@RequestParam(value = "userId") Integer userId){
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    @GetMapping("/getUserByMobileNumber")
    public ResponseEntity<UserDto> getUserByMobileNumber(@RequestParam(value = "mobileNumber") String mobileNumber){
        UserDto userByMobileNumber = userService.getUserByMobileNumber(mobileNumber);
        return new ResponseEntity<>(userByMobileNumber,HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam(value = "email") String email){
        UserDto userByEmail = userService.getUserByEmail(email);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<CursorPageResponse<UserDto>> getAllUsers(
            @RequestParam(value = "cursor",defaultValue = "0",required = false) Long cursor,
            @RequestParam(value = "size",defaultValue = "10",required = false) int size,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy
    ){
        CursorPageResponse<UserDto> allUsers = userService.getAllUsers(cursor, size, sortBy, sortDir);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "name") String name
            ){
        UserDto userDto = userService.updateUser(userId, name);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<ApiResponseMessage> deleteUser(
            @RequestParam(value = "userId") Integer userId
    ){
        userService.deleteUser(userId);
        ApiResponseMessage userDeletedSuccessfully = ApiResponseMessage.builder().message("user deleted successfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(userDeletedSuccessfully,HttpStatus.OK);
    }

}
