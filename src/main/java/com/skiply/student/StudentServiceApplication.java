package com.skiply.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Student Service API",
		version = "1.0",
		description = "API for managing student information"
))
public class StudentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
}