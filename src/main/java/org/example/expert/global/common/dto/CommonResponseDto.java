package org.example.expert.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.expert.global.enums.StatusCode;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponseDto<T> {
    private final String status;
    private final String message;
    private final T data;

    public static <T> CommonResponseDto<T> of(StatusCode statusCode, T data) {
        return CommonResponseDto.<T>builder()
                .status(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }

    public static CommonResponseDto<Void> of(StatusCode statusCode) {
        return CommonResponseDto.<Void>builder()
                .status(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(null)
                .build();
    }
}
