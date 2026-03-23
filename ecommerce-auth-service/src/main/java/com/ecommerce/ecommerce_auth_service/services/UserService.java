package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.dtos.CursorPageResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;

public interface UserService {

    public CursorPageResponse<UserDto> getAllUsers(Long cursor, int size, String sortBy, String sortDir);
}
