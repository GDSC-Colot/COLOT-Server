package com.gdsc.colot.jwt.filter;

import com.gdsc.colot.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gdsc.colot.utils.ServletErrorResponse.setErrorResponse;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("JWT 토큰 만료: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.TOKEN_TIME_EXPIRED_EXCEPTION);
        } catch (UsernameNotFoundException e) {
            log.error("사용자 찾기 실패: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.AUTHORIZE_FAILED_EXCEPTION);
        } catch (SignatureException e) {
            log.error("JWT 토큰 형식 이상: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION);
        }

    }

}
