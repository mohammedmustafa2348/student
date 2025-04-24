package com.skiply.student.controller;

import com.skiply.student.model.Student;
import com.skiply.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student API", description = "API for managing students")
@Slf4j
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        log.info("Received request to create student: {}", student);
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        log.info("Received request to get student with ID: {}", studentId);
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PutMapping("/{studentId}")
    @Operation(summary = "Update student")
    public ResponseEntity<Student> updateStudent(
            @PathVariable String studentId,
            @RequestBody Student studentDetails) {
        log.info("Received request to update student with ID: {}", studentId);
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentDetails));
    }

    @DeleteMapping("/{studentId}")
    @Operation(summary = "Delete student")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        log.info("Received request to delete student with ID: {}", studentId);
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/grade/{grade}")
    @Operation(summary = "Get students by grade")
    public ResponseEntity<List<Student>> getStudentsByGrade(@PathVariable String grade) {
        log.info("Received request to get students in grade: {}", grade);
        return ResponseEntity.ok(studentService.findByGrade(grade));
    }

    @GetMapping("/school/{schoolName}")
    @Operation(summary = "Get students by school name")
    public ResponseEntity<List<Student>> getStudentsBySchool(@PathVariable String schoolName) {
        log.info("Received request to get students from school: {}", schoolName);
        return ResponseEntity.ok(studentService.findBySchoolName(schoolName));
    }

}