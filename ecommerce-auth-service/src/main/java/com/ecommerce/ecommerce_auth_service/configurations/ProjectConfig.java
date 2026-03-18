package com.ecommerce.ecommerce_auth_service.configurations;
import com.ecommerce.ecommerce_auth_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    private final UserRepo repository;

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    // 1. FIRST - PasswordEncoder (no dependencies)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. SECOND - UserDetailsService (uses repository)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByMobileNumber(username)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with this mobile number !"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
//        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}