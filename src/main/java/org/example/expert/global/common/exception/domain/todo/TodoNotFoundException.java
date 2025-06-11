package org.example.expert.global.common.exception.domain.todo;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class TodoNotFoundException extends CustomException {
    public TodoNotFoundException() {
        super(StatusCode.NOT_FOUND, "존재하지 않는 Todo입니다.");
    }
}
