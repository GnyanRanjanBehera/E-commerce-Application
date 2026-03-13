package com.ecommerce.ecommerce_auth_service.services.impl;
import com.ecommerce.ecommerce_auth_service.domains.dtos.AuthResponse;
import com.ecommerce.ecommerce_auth_service.domains.dtos.UserDto;
import com.ecommerce.ecommerce_auth_service.domains.entities.Token;
import com.ecommerce.ecommerce_auth_service.domains.entities.User;
import com.ecommerce.ecommerce_auth_service.domains.enums.TokenType;
import com.ecommerce.ecommerce_auth_service.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce_auth_service.repositories.TokenRepo;
import com.ecommerce.ecommerce_auth_service.repositories.UserRepo;
import com.ecommerce.ecommerce_auth_service.security.JwtService;
import com.ecommerce.ecommerce_auth_service.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    final private UserRepo userRepo;
    final private TokenRepo tokenRepo;
    final private ModelMapper mapper;
    final private JwtService jwtService;


    @Override
    public AuthResponse signUp(UserDto userDto) {
        User user = User.builder().name(userDto.getName())
                .mobileNumber(userDto.getMobileNumber())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        Token token = Token.builder().accessToken("").refreshToken("").user(user).build();
        tokenRepo.save(token);
        AuthResponse user1 = AuthResponse.builder().accessToken("").refreshToken("").user(mapper.map(user, UserDto.class)).build();
        return user1;
    }

    @Override
    public AuthResponse signIn(String mobileNumber, String password) {
        User user = userRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("user not found"));
//        return mapper.map(user,UserDto.class);
        return null;
    }


//    @Override
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String mobileNumber;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        mobileNumber = jwtService.extractUsername(refreshToken);
//        if (mobileNumber != null) {
//            var user = this.userRepository.findByMobileNumber(mobileNumber)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }


    private AuthResponse generateAuthResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(mapper.map(user, UserDto.class))
                .build();
    }


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .accessToken(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }
}
