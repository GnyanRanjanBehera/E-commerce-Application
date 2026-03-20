package com.ecommerce.ecommerce_auth_service.aspects;


import com.ecommerce.ecommerce_auth_service.annotations.AccessControl;
import com.ecommerce.ecommerce_auth_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_auth_service.security.JwtService;
import com.ecommerce.ecommerce_auth_service.services.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessControlAspect {

    private final PermissionService permissionService;
    private final JwtService jwtService;

    public void checkAccessControl(JoinPoint joinPoint, AccessControl accessControl){
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                throw new ResourceNotFoundException("Unable to access request context");
            }
            HttpServletRequest request = attributes.getRequest();
            String authorization = request.getHeader("Authorization");
            String mobileNumber=jwtService.extractUsername(authorization.substring(7));
            if (mobileNumber == null || mobileNumber.isEmpty()) {

                throw new ResourceNotFoundException("User not authenticated - mobileNumber not found in request");
            }
            boolean hasAccess = permissionService.hasAccess(mobileNumber, accessControl.controllerType(), accessControl.methodType());
            if (!hasAccess) {
                throw new ResourceNotFoundException("Access denied - insufficient permissions for operation: " + accessControl.methodType() + " on " + accessControl.controllerType());
            }
        }catch (ResourceNotFoundException e) {
            throw e;
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Internal server error during authorization");
        }

    }

}
