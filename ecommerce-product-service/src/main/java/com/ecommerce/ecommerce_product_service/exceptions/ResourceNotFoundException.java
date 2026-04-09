package com.ecommerce.ecommerce_product_service.exceptions;

public class ResourceNotFoundException extends   RuntimeException{
    public  ResourceNotFoundException(String message){
        super(message);
    }
}
