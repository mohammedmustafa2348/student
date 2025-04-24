package com.skiply.student.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String studentId) {
        super("Student already exists with ID: " + studentId);
    }
}