package com.ecommerce.ecommerce_auth_service.services.impl;

import com.ecommerce.ecommerce_auth_service.domains.dtos.CursorPageResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    @Override
    public CursorPageResponse<UserDto> getAllUsers(Long cursor, int size,String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(0, size,sort);
        List<User> users = userRepo.fetchAllUsers(cursor, pageable);
        boolean hasNext = users.size() == size;
        Integer nextCursor = hasNext
                ? users.get(users.size() - 1).getUserId()
                : null;
        List<UserDto> userDto = users.stream().map(e -> mapper.map(e, UserDto.class)).toList();
        return new CursorPageResponse<>(
                userDto,
                size,
                nextCursor,
                hasNext
        );
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with this id"));
        return mapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserByMobileNumber(String mobileNumber) {
        User user = userRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("User not found with mobile number"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id"));
        return mapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(Integer userId, String name) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this id"));
        if(!name.isEmpty()){
            user.setName(name);
        }
        User saveuser = userRepo.save(user);
        return mapper.map(saveuser,UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found by this id"));
        userRepo.delete(user);
    }
}
