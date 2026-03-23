package com.ecommerce.ecommerce_auth_service.services.impl;
import com.ecommerce.ecommerce_auth_service.domains.entities.Permission;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import com.ecommerce.ecommerce_auth_service.domains.enums.MethodType;
import com.ecommerce.ecommerce_auth_service.exceptions.BadApiRequestException;
import com.ecommerce.ecommerce_auth_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_auth_service.repositories.PermissionRepo;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.ecommerce.ecommerce_auth_service.domains.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepo permissionRepo;

    private final UserRepo userRepo;

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

    @Override
    public void saveDefaultPermission(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new BadApiRequestException("user not found"));
        switch (user.getRole()) {
            case ADMIN, USER:
                for (ControllerType type : ControllerType.values()) {
                    Permission userPer = Permission.builder().user(user).controllerType(type).isActive(true)
                            .canRead(true).canWrite(true).canUpdate(true).canPatch(true).canDelete(true)
                            .build();
                    permissionRepo.save(userPer);
                }
                break;
            case OPERATOR:
                for (ControllerType type : ControllerType.values()) {
                    Permission userPer = Permission.builder().user(user).controllerType(type).isActive(true)
                            .canRead(true).canWrite(false).canUpdate(true)
                            .canPatch(true).canDelete(false).build();
                    permissionRepo.save(userPer);
                }
                break;
            default:
                throw new ResourceNotFoundException("You did not found user role");

        }
    }
}
