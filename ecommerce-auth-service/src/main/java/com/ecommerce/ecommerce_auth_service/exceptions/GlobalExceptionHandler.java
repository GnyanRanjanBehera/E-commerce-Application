package com.ecommerce.ecommerce_auth_service.exceptions;
import com.ecommerce.ecommerce_auth_service.domains.dtos.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException exception){
            ApiResponseMessage responseMessage = ApiResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BadApiRequestException.class)
        public ResponseEntity<ApiResponseMessage> badApiRequest(BadApiRequestException exception){
            ApiResponseMessage responseMessage = ApiResponseMessage.builder().message(exception.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
}
