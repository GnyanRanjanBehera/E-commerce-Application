package com.ecommerce.ecommerce_auth_service.domains.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {
    private  String message;
    private  boolean success;
    private HttpStatus status;
}
