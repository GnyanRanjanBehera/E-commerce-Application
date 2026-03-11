package com.ecommerce.ecommerce_auth_service.domains.dtos;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer userId;

    private String name;

    private String mobileNumber;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
