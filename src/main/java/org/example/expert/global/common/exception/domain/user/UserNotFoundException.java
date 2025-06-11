package org.example.expert.global.common.exception.domain.user;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(StatusCode.NOT_FOUND, "존재하지 않는 사용자입니다.");
    }
}
