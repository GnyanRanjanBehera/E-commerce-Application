package com.ecommerce.ecommerce_auth_service.repositories;

import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByMobileNumber(String mobileNumber);
    Optional<User> findByEmail(String email);

    @Query("""
        SELECT u FROM User u
        WHERE (:cursor  IS NULL OR u.userId>:cursor)
        ORDER BY u.userId ASC
        """)
    public List<User> fetchAllUsers(@Param("cursor") Long cursor, Pageable pageable);

}
