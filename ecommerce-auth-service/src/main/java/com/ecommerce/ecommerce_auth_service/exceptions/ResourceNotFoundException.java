package com.ecommerce.ecommerce_auth_service.exceptions;

public class ResourceNotFoundException extends   RuntimeException{
   public ResourceNotFoundException(String message){
        super(message);
    }
}
