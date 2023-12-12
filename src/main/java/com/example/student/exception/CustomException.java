package com.example.student.exception;

import java.util.AbstractMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.example.student.entity.ErrorCode;

import lombok.Getter;

public class CustomException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;
    private String message;

    @Getter
    private Map.Entry<String, Object> data;

    @Override
    public String getMessage() {
        if (StringUtils.hasLength(this.message)) {
            return this.message;
        }
        return errorCode.getMessage();
    }

    public CustomException(ErrorCode errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = new AbstractMap.SimpleEntry<>(data.getClass().getSimpleName(), data);
    }
}
