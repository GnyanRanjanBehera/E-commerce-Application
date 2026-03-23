package com.ecommerce.ecommerce_auth_service.annotations;
import com.ecommerce.ecommerce_auth_service.domains.enums.ControllerType;
import com.ecommerce.ecommerce_auth_service.domains.enums.MethodType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessControl {
    ControllerType controllerType();
    MethodType methodType();
}
