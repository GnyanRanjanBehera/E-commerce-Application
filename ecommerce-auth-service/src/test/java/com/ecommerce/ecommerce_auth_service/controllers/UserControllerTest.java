package com.ecommerce.ecommerce_auth_service.controllers;

import com.ecommerce.ecommerce_auth_service.domains.dtos.ApiResponseMessage;
import com.ecommerce.ecommerce_auth_service.domains.dtos.CursorPageResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import com.ecommerce.ecommerce_auth_service.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp(){
        userDto=UserDto.builder()
                .userId(1)
                .name("Test")
                .email("test@gmail.com")
                .mobileNumber("1234567890")
                .password("password")
                .role(Role.USER)
                .build();

    }

    @Test
    @DisplayName("Update User")
    public void updateUser(){
        when(userService.updateUser(anyInt(),anyString())).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.updateUser(1, "Test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto,response.getBody());
        verify(userService,times(1)).updateUser(1,"Test");
    }

    @Test
    @DisplayName("User By Id")
    public void getUserById(){
        when(userService.getUserById(anyInt())).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.getUserById(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto,response.getBody());
        verify(userService,times(1)).getUserById(1);

    }

    @Test
    @DisplayName("UserByEmail")
    public void getUserByEmail(){
        when(userService.getUserByEmail(anyString())).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.getUserByEmail("test@gmail.com");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto,response.getBody());
        verify(userService,times(1)).getUserByEmail("test@gmail.com");
    }

    @Test
    @DisplayName("getUserByMobileNumber")
    public void getUserByMobileNumber(){
        when(userService.getUserByMobileNumber(anyString())).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.getUserByMobileNumber("1234567890");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto,response.getBody());
        verify(userService,times(1)).getUserByMobileNumber("1234567890");
    }

    @Test
    @DisplayName("getAllUsers")
    public  void getAllUsers(){
        CursorPageResponse<UserDto> cursorResponse = new CursorPageResponse<>(List.of(userDto),1,null,false);
        when(userService.getAllUsers(anyLong(),anyInt(),anyString(),anyString())).thenReturn(cursorResponse);
        ResponseEntity<CursorPageResponse<UserDto>> response =
                userController.getAllUsers(1L, 1, "asc", "name");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cursorResponse,response.getBody());
        verify(userService, times(1))
                .getAllUsers(1L, 1, "name", "asc");
    }

    @Test
    @DisplayName("Deleted user")
    public void deleteUser(){
        ApiResponseMessage userDeletedSuccessfully = ApiResponseMessage.builder().message("user deleted successfully").success(true).status(HttpStatus.OK).build();
        ResponseEntity<ApiResponseMessage> response = userController.deleteUser(1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDeletedSuccessfully.isSuccess(),response.getBody().isSuccess());
        verify(userService,times(1)).deleteUser(1);
    }
}
