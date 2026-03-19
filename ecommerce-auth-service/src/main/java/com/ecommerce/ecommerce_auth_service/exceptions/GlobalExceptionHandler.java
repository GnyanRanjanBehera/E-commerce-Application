package com.ecommerce.ecommerce_auth_service.exceptions;
import com.ecommerce.ecommerce_auth_service.domains.dtos.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,Object>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException exception){
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            Map<String,Object> response=new HashMap<>();
            allErrors.forEach(objectError -> {
            String message=objectError.getDefaultMessage();
            String field=((FieldError) objectError).getField();
            response.put(field,message);
            });
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
}
