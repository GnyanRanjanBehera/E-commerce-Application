package com.ecommerce.ecommerce_auth_service.domains.entities;


import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "per_id",nullable = false,unique = true)
    private Integer perId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "controller_type",nullable = false)
    private ControllerType controllerType;

    @Column(nullable = false,name = "can_read")
    private boolean canRead;

    @Column(nullable = false,name = "can_write")
    private boolean canWrite;

    @Column(nullable = false,name = "can_update")
    private boolean canUpdate;

    @Column(nullable = false,name = "can_delete")
    private boolean canDelete;

    @Column(nullable = false,name = "can_patch")
    private boolean canPatch;

    @Column(nullable = false,name = "is_active")
    private boolean isActive;

    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false,name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();
    }

}
