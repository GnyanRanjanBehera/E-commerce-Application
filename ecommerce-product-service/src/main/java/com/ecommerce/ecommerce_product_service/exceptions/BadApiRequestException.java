package com.ecommerce.ecommerce_product_service.exceptions;

public class BadApiRequestException extends RuntimeException{
    public  BadApiRequestException(){
        super("Bad API request");
    }
    public BadApiRequestException(String message){
        super(message);
    }
}
