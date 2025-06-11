package org.example.expert.global.config.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminApiLoggingAspect {
    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;

    @Around("@annotation(org.example.expert.global.common.annotation.AdminApi)")
    public Object logAdminApiAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId =(Long) request.getAttribute("userId");
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        Object[] args = joinPoint.getArgs();
        String requestBody = parseObjectToJson(args);

        log.info("[ADMIN API REQUEST] userId: {}, time: {}, url: {}, requestBody: {}",
                userId, requestTime, requestUrl, requestBody);

        Object result = joinPoint.proceed();

        String responseBody = parseObjectToJson(result);

        log.info("[ADMIN API RESPONSE] userId: {}, time: {}, url: {}, responseBody: {}",
                userId, requestTime, requestUrl, responseBody);
        return result;
    }

    public String parseObjectToJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }
        catch (Exception e){
                return "직렬화 실패" + e.getMessage();
        }
    }
}
