package com.example.surfer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_MEMBER(HttpStatus.FORBIDDEN, "아이디 또는 비밀번호가 틀렸습니다"),

    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),

    INVALID_FORM_DATA(HttpStatus.BAD_REQUEST,"유효하지 않은 형식의 데이터 입니다."),
    INVALID_REPEATED_PASSWORD(HttpStatus.BAD_REQUEST,"비밀번호를 정확히 입력해주세요."),

    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INCORRECT_VERIFICATION_CODE(HttpStatus.UNAUTHORIZED, "인증 번호가 일치하지 않습니다."),
    MISMATCH_PHONENUMBER(HttpStatus.BAD_REQUEST, "입력된 전화번호가 등록된 전화번호와 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}