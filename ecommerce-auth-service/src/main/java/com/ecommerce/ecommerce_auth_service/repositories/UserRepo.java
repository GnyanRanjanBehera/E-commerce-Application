package com.ecommerce.ecommerce_auth_service.repositories;

import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByMobileNumber(String mobileNumber);
}
