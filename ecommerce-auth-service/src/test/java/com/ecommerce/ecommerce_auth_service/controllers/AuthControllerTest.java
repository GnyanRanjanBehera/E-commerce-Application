package com.ecommerce.ecommerce_auth_service.controllers;
import com.ecommerce.ecommerce_auth_service.domains.dtos.AuthResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import com.ecommerce.ecommerce_auth_service.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    private UserDto user;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp(){
        user=UserDto.builder()
                .userId(1)
                .name("Test")
                .email("test@gmial.com")
                .mobileNumber("1234567890")
                .password("123456")
                .role(Role.USER)
                .build();
        authResponse=AuthResponse.builder()
                .accessToken("1234567890")
                .refreshToken("123455678900")
                .user(user)
                .build();



    }

    @Test
    @DisplayName("SignUp Test")
    void signUpTest(){
        when(authService.signUp(any())).thenReturn(authResponse);
        ResponseEntity<AuthResponse> response = authController.signUp(user);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(authResponse,response.getBody());
        verify(authService,times(1)).signUp(user);
    }

    @Test
    @DisplayName("SignIn Test")
    void signInTest(){
        when(authService.signIn(anyString(),anyString())).thenReturn(authResponse);
        ResponseEntity<AuthResponse> response = authController.signIn("1234567890", "123456");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(authResponse,response.getBody());
        verify(authService,times(1)).signIn("1234567890","123456");

    }






}
