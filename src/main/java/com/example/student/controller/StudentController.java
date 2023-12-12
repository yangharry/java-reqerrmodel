package com.example.student.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.entity.ApiResponse;
import com.example.student.entity.ErrorCode;
import com.example.student.entity.Student;
import com.example.student.exception.CustomException;
import com.example.student.exception.InputRestriction;
import com.example.student.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public ApiResponse<Student> add(@RequestParam("name") String name, @RequestParam("grade") int grade) {
        if (grade >= 6) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "grade는 6 이상을 입력할 수 없습니다.", new InputRestriction(6));
        }
        return makeResponse(studentService.addStudent(name, grade));
    }

    @GetMapping("/students")
    public ApiResponse<Student> getAll() {
        return makeResponse(studentService.getAll());
    }

    @GetMapping("/students/{grade}")
    public ApiResponse<Student> getGradeStudent(
            @PathVariable("grade") int grade) {
        return makeResponse(studentService.getGradeStudent(grade));
    }

    public <T> ApiResponse<T> makeResponse(List<T> reuslt) {
        return new ApiResponse<>(reuslt);
    }

    public <T> ApiResponse<T> makeResponse(T result) {
        return makeResponse(Collections.singletonList(result));
    }

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> customExceptionHandler(HttpServletResponse response, CustomException customException) {
        return new ApiResponse<>(customException.getErrorCode().getCode(), customException.getErrorCode().getMessage(),
                customException.getData());
    }

}
