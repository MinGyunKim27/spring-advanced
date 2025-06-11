package org.example.expert.global.common.exception;

import lombok.Getter;
import org.example.expert.global.enums.StatusCode;

@Getter
public abstract class CustomException extends RuntimeException {
    private final StatusCode statusCode;

    public CustomException(StatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
