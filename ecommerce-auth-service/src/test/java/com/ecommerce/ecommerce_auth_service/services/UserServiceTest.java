package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.dtos.CursorPageResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    private UserDto userDto;

    private User user;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp(){
        userDto=UserDto.builder()
                .userId(1)
                .name("Test")
                .email("test@gmail.com")
                .mobileNumber("1234567890")
                .role(Role.USER)
                .password("password")
                .build();
        user=User.builder()
                .userId(1)
                .name("Test")
                .email("test@gmail.com")
                .mobileNumber("1234567890")
                .role(Role.USER)
                .password("password")
                .build();

    }


    @Test
    @DisplayName("getUserById")
    public void getUserById(){
        when(userRepo.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        when(mapper.map(any(), eq(UserDto.class))).thenReturn(userDto);
        UserDto userById = userService.getUserById(1);
        assertNotNull(userById);
        assertEquals(userById,userDto);
        verify(userRepo,times(1)).findById(1);
    }

    @Test
    @DisplayName("getUserByEmail")
    public void  getUserByEmail(){
        when(userRepo.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(mapper.map(any(),eq(UserDto.class))).thenReturn(userDto);
        UserDto userByEmail = userService.getUserByEmail("test@gmail.com");
        assertNotNull(userByEmail);
        assertEquals(userDto,userByEmail);
        verify(userRepo,times(1)).findByEmail("test@gmail.com");
    }

    @Test
    @DisplayName("getUserByMobileNumber")
    public void getUserByMobileNumber(){
        when(userRepo.findByMobileNumber(anyString())).thenReturn(Optional.of(user));
        when(mapper.map(any(),eq(UserDto.class))).thenReturn(userDto);
        UserDto userByMobileNumber = userService.getUserByMobileNumber("1234567890");
        assertNotNull(userByMobileNumber);
        assertEquals(userDto,userByMobileNumber);
        verify(userRepo,times(1)).findByMobileNumber("1234567890");

    }

    @Test
    @DisplayName("getAllUsers")
    public void getAllUsers(){
        when(userRepo.fetchAllUsers(anyLong(),any(Pageable.class))).thenReturn(List.of(user));
        when(mapper.map(any(),eq(UserDto.class))).thenReturn(userDto);
        CursorPageResponse<UserDto> response =
                userService.getAllUsers(1L, 1, "name", "asc");
        assertNotNull(response);
        assertEquals(1, response.data().size());
        assertTrue(response.hasNext());
        verify(userRepo, times(1))
                .fetchAllUsers(anyLong(), any(Pageable.class));
    }

    @Test
    @DisplayName("updateUser")
    public  void updateUser(){
        when(userRepo.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        when(userRepo.save(any())).thenReturn(user);
        when(mapper.map(any(),eq(UserDto.class))).thenReturn(userDto);
        UserDto response = userService.updateUser(1, "Test");
        assertNotNull(response);
        assertEquals("Test",response.getName());
        verify(userRepo,times(1)).save(user);

    }

    @Test
    @DisplayName("deleteUser")
    public  void deleteUser(){
        when(userRepo.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        userService.deleteUser(1);
        verify(userRepo, times(1)).findById(1);
        verify(userRepo,times(1)).delete(user);
    }
}
