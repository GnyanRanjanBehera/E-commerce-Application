package com.ecommerce.ecommerce_auth_service.domains.dtos;
import com.ecommerce.ecommerce_auth_service.domains.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "name can not be blank")
    private String name;

    @NotBlank(message = "mobileNumber can not be blank")
    @Size(min = 10,max = 10,message = "mobileNumber should be 10 digit")
    private String mobileNumber;

    @NotBlank(message = "email can not be blank")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "password can not be blank")
    @Size(min = 6,max = 10,message = "password should be 6 to 10 digit")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
