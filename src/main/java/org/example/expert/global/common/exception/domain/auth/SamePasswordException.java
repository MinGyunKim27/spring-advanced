package org.example.expert.global.common.exception.domain.auth;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class SamePasswordException extends CustomException {
    public SamePasswordException() {
        super(StatusCode.BAD_REQUEST,"새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
    }
}
