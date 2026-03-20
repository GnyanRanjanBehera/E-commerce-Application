package com.ecommerce.ecommerce_auth_service.domains.dtos;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDto {

    private Integer perId;

    private Integer userId;

    private ControllerType controllerType;

    private boolean canRead;

    private boolean canWrite;

    private boolean canUpdate;

    private boolean canDelete;

    private boolean canPatch;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private LocalDateTime updatedAt;

}
