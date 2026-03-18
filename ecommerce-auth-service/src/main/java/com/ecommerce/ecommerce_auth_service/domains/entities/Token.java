package com.ecommerce.ecommerce_auth_service.domains.entities;

import com.ecommerce.ecommerce_auth_service.domains.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id",nullable = false,unique = true)
    private Integer tokenId;

    @Column(name ="token",nullable = false,unique = true)
    private String token;

    @Column(name = "token_type",nullable = false)
    private TokenType tokenType=TokenType.BEARER;

    @Column(name = "revoked",nullable = false)
    private boolean revoked=false;

    @Column(name = "expired",nullable = false)
    private boolean expired=false;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

}
