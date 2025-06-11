package org.example.expert.global.common.exception.domain.todo;

import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.enums.StatusCode;

public class TodoUserMissingException extends CustomException {
    public TodoUserMissingException() {
        super(StatusCode.BAD_REQUEST,"해당 Todo에 연관된 사용자가 존재하지 않습니다!");
    }
}
