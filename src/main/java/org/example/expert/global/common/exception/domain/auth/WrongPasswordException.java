package org.example.expert.global.common.exception.domain.auth;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class WrongPasswordException extends CustomException {
    public WrongPasswordException() {
        super(StatusCode.BAD_REQUEST, "잘못된 비밀번호입니다.");
    }
}
