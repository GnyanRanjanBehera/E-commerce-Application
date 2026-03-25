package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.dtos.AuthResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import com.ecommerce.ecommerce_auth_service.repositories.TokenRepo;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.security.JwtService;
import com.ecommerce.ecommerce_auth_service.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private TokenRepo tokenRepo;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PermissionService permissionService;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private AuthServiceImpl authService;

    private UserDto user;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp(){
        user=UserDto.builder()
                .userId(1)
                .name("Test")
                .email("test@gmail.com")
                .mobileNumber("1234567890")
                .password("123456")
                .role(Role.USER)
                .build();
        authResponse=AuthResponse.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .user(user)
                .build();

    }

    @Test
    @DisplayName("SignIn Test")
    void signInTest(){
        User savedUser = User.builder()
                .userId(1)
                .name("Test")
                .email("test@gmail.com")
                .mobileNumber("1234567890")
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        // ✅ Mock behavior
        when(userRepo.findByMobileNumber(any())).thenReturn(Optional.empty());
        when(userRepo.findByEmail(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepo.save(any())).thenReturn(savedUser); // ✅ FIXED

        when(jwtService.generateToken(any())).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any())).thenReturn("refreshToken");

        when(tokenRepo.findAllValidTokenByUser(any())).thenReturn(List.of());
        when(mapper.map(any(), eq(UserDto.class))).thenReturn(user);

        // ✅ Call actual method
        AuthResponse response = authService.signUp(user);

        // ✅ Assertions
        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());

        // ✅ Verify interactions
        verify(userRepo, times(1)).save(any());
        verify(permissionService, times(1)).saveDefaultPermission(savedUser.getUserId());

    }


    @Test
    @DisplayName("SignIn Success Test")
    void signInSuccessTest() {

        // ✅ Create User entity
        User savedUser = User.builder()
                .userId(1)
                .mobileNumber("1234567890")
                .password("encodedPassword")
                .build();

        // ✅ Mock behavior
        when(userRepo.findByMobileNumber(any()))
                .thenReturn(Optional.of(savedUser));

        when(passwordEncoder.matches(any(), any()))
                .thenReturn(true);

        when(jwtService.generateToken(any()))
                .thenReturn("accessToken");

        when(jwtService.generateRefreshToken(any()))
                .thenReturn("refreshToken");

        when(tokenRepo.findAllValidTokenByUser(any()))
                .thenReturn(List.of());

        when(mapper.map(any(), eq(UserDto.class)))
                .thenReturn(user);

        // ✅ Call method
        AuthResponse response = authService.signIn("1234567890", "123456");

        // ✅ Assertions
        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());

        // ✅ Verify
        verify(userRepo, times(1)).findByMobileNumber(any());
        verify(passwordEncoder, times(1)).matches(any(), any());
    }


}
