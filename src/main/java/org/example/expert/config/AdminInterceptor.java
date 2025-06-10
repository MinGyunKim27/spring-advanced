package org.example.expert.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDate;

@Slf4j(topic = "AdminInterceptor")
@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object Handler
    ) throws  Exception{
        String tokenValue = request.getHeader("Authorization");

        if (tokenValue==null || !tokenValue.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = jwtUtil.substringToken(tokenValue);
        Claims claims = jwtUtil.extractClaims(token);

        String role = claims.get("userRole", String.class);

        if (!"ADMIN".equals(role)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        log.info("[ADMIN ACCESS] {} - {} ", LocalDate.now(),request.getRequestURI());

        return true;
    }

}
