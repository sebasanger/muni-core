package com.sanger.muni.controllers;

import javax.validation.Valid;

import com.sanger.muni.dto.auth.AuthenticationResponse;
import com.sanger.muni.dto.auth.LoginRequestDto;
import com.sanger.muni.dto.auth.RefreshTokenRequestDto;
import com.sanger.muni.dto.auth.RefreshTokenResponse;
import com.sanger.muni.dto.auth.ValidateUserDto;
import com.sanger.muni.dto.auth.ValidateUserTokenDto;
import com.sanger.muni.dto.user.GetUsersDto;
import com.sanger.muni.jwt.JwtProvider;
import com.sanger.muni.model.UserEntity;
import com.sanger.muni.services.AuthService;
import com.sanger.muni.services.RefreshTokenService;
import com.sanger.muni.services.VerificationTokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final com.sanger.muni.dto.user.UserDtoConverter converter;
    private final VerificationTokenService verificationTokenService;
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public RefreshTokenResponse refreshTokens(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public GetUsersDto me(@AuthenticationPrincipal UserEntity user) {
        return converter.convertUserEntityToGetUserDto(user);
    }

    @GetMapping("/validate-token")
    public boolean validateUserToken(@Valid ValidateUserTokenDto userToken) {
        return jwtProvider.validateToken(userToken.getToken());
    }

    @PutMapping("/validate-acount")
    public ResponseEntity<Boolean> updateUser(@Valid @RequestBody ValidateUserDto validation) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(verificationTokenService.validateVerificationToken(validation));

    }

}
