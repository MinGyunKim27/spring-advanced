package org.example.expert.global.common.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.example.expert.domain.auth.exception.AuthException;
import org.example.expert.global.common.exception.CustomException;
import org.example.expert.global.common.exception.InvalidRequestException;
import org.example.expert.global.common.exception.domain.server.ServerException;
import org.example.expert.global.common.dto.CommonResponseDto;
import org.example.expert.global.enums.StatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleInvalidRequest(InvalidRequestException ex) {
        return buildErrorResponse(StatusCode.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleAuthException(AuthException ex) {
        return buildErrorResponse(StatusCode.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleServerException(ServerException ex) {
        return buildErrorResponse(StatusCode.INTERNAL_ERROR, ex.getMessage());
    }

    //CustomException을 처리하는 클래스

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleCustomException(CustomException ex) {
        return buildErrorResponse(ex.getStatusCode(), ex.getMessage());
    }


    // 스프링 내장 예외

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return buildErrorResponse(StatusCode.BAD_REQUEST, message);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleBindException(BindException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return buildErrorResponse(StatusCode.BAD_REQUEST, message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleMissingParameter(MissingServletRequestParameterException ex) {
        String message = "필수 요청 파라미터가 누락되었습니다: " + ex.getParameterName();
        return buildErrorResponse(StatusCode.BAD_REQUEST, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponseDto<Void>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().iterator().next().getMessage();
        return buildErrorResponse(StatusCode.BAD_REQUEST, message);
    }

    // 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponseDto<Void>> handleUnexpected(Exception ex) {
        return buildErrorResponse(StatusCode.INTERNAL_ERROR, "예상치 못한 오류가 발생했습니다.");
    }

    // 공통 응답 생성
    private ResponseEntity<CommonResponseDto<Void>> buildErrorResponse(StatusCode statusCode, String message) {
        return ResponseEntity
                .status(statusCode.getHttpStatus())
                .body(new CommonResponseDto<>(statusCode.getCode(), message, null));
    }
}
