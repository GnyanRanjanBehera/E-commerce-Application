package com.ecommerce.ecommerce_auth_service.services;

import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import com.ecommerce.ecommerce_auth_service.domains.enums.MethodType;

public interface PermissionService {
    public boolean hasAccess(String  mobileNumber, ControllerType controllerType, MethodType methodType);
}
