package com.ecommerce.ecommerce_auth_service.services.impl;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    final private UserRepo userRepo;
    final private ModelMapper mapper;

    @Override
    public UserDto signUp(UserDto userDto) {
        User user = User.builder().name(userDto.getName())
                .mobileNumber(userDto.getMobileNumber())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        User saveUser = userRepo.save(user);
        return mapper.map(saveUser,UserDto.class);
    }

    @Override
    public UserDto signIn(String mobileNumber, String password) {
        User user = userRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("user not found"));
        return mapper.map(user,UserDto.class);
    }
}
