package com.gdsc.colot.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    RE_ISSUE_TOKEN_SUCCESS(HttpStatus.OK, "토큰 재발급을 성공했습니다"),
    GET_USER_INFO_SUCCESS(HttpStatus.OK, "사용자 정보 조회에 성공했습니다"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),

    /**
     * 201 CREATED
     */
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    SIGNOUT_SUCCESS(HttpStatus.CREATED, "로그아웃이 완료됐습니다."),
    CAR_CREATED_SUCCESS(HttpStatus.CREATED, "차량 등록에 성공했습니다"),
    PARKING_LOT_CREATED_SUCCESS(HttpStatus.CREATED, "주차장 등록에 성공했습니다"),

    /**
     * 204 NO_CONTENT
     */
    UNLINK_SUCCESS(HttpStatus.NO_CONTENT, "사용자 탈퇴에 성공했습니다"),
    ;


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
