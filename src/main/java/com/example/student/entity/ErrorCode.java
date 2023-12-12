package com.example.student.entity;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public enum ErrorCode {
    OK(2000, "OK", HttpStatus.OK),
    BAD_REQUEST(5000, "Bad Request", HttpStatus.OK);

    @Getter
    private final int code;

    @Getter
    private final String message;

    @Getter
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
