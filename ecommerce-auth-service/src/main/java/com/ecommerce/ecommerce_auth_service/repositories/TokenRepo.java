package com.ecommerce.ecommerce_auth_service.repositories;
import com.ecommerce.ecommerce_auth_service.domains.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token,Integer> {

}
