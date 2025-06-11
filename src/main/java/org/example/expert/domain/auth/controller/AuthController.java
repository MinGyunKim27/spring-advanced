package org.example.expert.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.auth.dto.request.SigninRequest;
import org.example.expert.domain.auth.dto.request.SignupRequest;
import org.example.expert.domain.auth.dto.response.SigninResponse;
import org.example.expert.domain.auth.dto.response.SignupResponse;
import org.example.expert.domain.auth.service.AuthService;
import org.example.expert.global.common.dto.CommonResponseDto;
import org.example.expert.global.enums.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<CommonResponseDto<SignupResponse>> signup(
            @Valid @RequestBody SignupRequest signupRequest) {
        SignupResponse signup = authService.signup(signupRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponseDto.of(StatusCode.CREATED, signup));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<CommonResponseDto<SigninResponse>> signin(
            @Valid @RequestBody SigninRequest signinRequest) {
        SigninResponse signin = authService.signin(signinRequest);

        return ResponseEntity
                .ok(CommonResponseDto.of(StatusCode.OK, signin));
    }

}
