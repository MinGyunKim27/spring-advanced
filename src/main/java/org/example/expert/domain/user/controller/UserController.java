package org.example.expert.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.global.common.annotation.Auth;
import org.example.expert.global.common.dto.AuthUser;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.example.expert.global.common.dto.CommonResponseDto;
import org.example.expert.global.enums.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<CommonResponseDto<UserResponse>> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(
                CommonResponseDto.of(StatusCode.OK,userService.getUser(userId)));
    }

    @PutMapping("/users")
    public ResponseEntity<CommonResponseDto<Void>> changePassword(@Auth AuthUser authUser, @RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        userService.changePassword(authUser.getId(), userChangePasswordRequest);

        return ResponseEntity.ok(
                CommonResponseDto.of(StatusCode.OK)
        );
    }
}
