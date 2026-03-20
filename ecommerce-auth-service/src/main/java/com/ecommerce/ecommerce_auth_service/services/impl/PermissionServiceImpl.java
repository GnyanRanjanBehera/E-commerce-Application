package com.ecommerce.ecommerce_auth_service.services.impl;
import com.ecommerce.ecommerce_auth_service.domains.entities.Permission;
import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import com.ecommerce.ecommerce_auth_service.domains.enums.MethodType;
import com.ecommerce.ecommerce_auth_service.repositories.PermissionRepo;
import com.ecommerce.ecommerce_auth_service.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepo permissionRepo;

    @Override
    public boolean hasAccess(String mobileNumber, ControllerType controllerType, MethodType methodType) {
        Optional<Permission> permission = permissionRepo
                .findByMobileNumberAndControllerType(mobileNumber, controllerType);
        if (permission.isEmpty()) {
            return false;
        }
        Permission userPermission = permission.get();
        return switch (methodType.name().toLowerCase()) {
            case "read" -> userPermission.isCanRead();
            case "write", "create" -> userPermission.isCanWrite();
            case "update" -> userPermission.isCanUpdate();
            case "delete" -> userPermission.isCanDelete();
            case "patch" -> userPermission.isCanPatch();
            default -> {
                yield false;
            }
        };
    }
}
