package com.ecommerce.ecommerce_auth_service.repositories;

import com.ecommerce.ecommerce_auth_service.domains.entities.Permission;
import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Integer> {
    @Query("SELECT p FROM Permission AS p WHERE p.user.mobileNumber=:moileNumber AND p.controllerType=:controllerType AND p.isActive=true")
    Optional<Permission> findByMobileNumberAndControllerType(@Param("mobileNumber") String mobileNumber, @Param("controllerType") ControllerType controllerType);
}
