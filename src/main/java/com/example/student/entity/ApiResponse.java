package com.example.student.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    @Getter
    @AllArgsConstructor
    private static class Status {
        private int code;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    private static class Metadata {
        private int resultCont = 0;
    }

    private final Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> results;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Metadata metadata;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    public ApiResponse(List<T> results) {
        this.status = new Status(2000, "OK");
        this.results = results;
        this.metadata = new Metadata(results.size());
    }

    public ApiResponse(int code, String message, Object data) {
        this.status = new Status(code, message);
        this.data = data;
    }

}
