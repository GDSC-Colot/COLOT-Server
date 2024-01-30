package com.gdsc.colot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ServletErrorResponse {

    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(
                    objectMapper.writeValueAsString(BaseResponse.error(errorCode, errorCode.getMessage()))
            );
        } catch (IOException e) {
            log.error("입출력 에러: {}", e.getMessage(), e);
        }
    }

}
