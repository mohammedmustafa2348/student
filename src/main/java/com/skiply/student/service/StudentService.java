//package com.example.student.service;
//
//import com.example.student.model.Student;
//import com.example.student.repository.StudentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class StudentService {
//    private final StudentRepository studentRepository;
//
//    public Student createStudent(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }
//
//    public Student getStudentById(String studentId) {
//        return studentRepository.findByStudentId(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//    }
//
//    public Student updateStudent(String studentId, Student studentDetails) {
//        Student student = getStudentById(studentId);
//        student.setName(studentDetails.getName());
//        student.setGrade(studentDetails.getGrade());
//        student.setMobileNumber(studentDetails.getMobileNumber());
//        student.setSchoolName(studentDetails.getSchoolName());
//        return studentRepository.save(student);
//    }
//
//    public void deleteStudent(String studentId) {
//        Student student = getStudentById(studentId);
//        studentRepository.delete(student);
//    }
//}

package com.skiply.student.service;

import com.skiply.student.exception.InvalidStudentDataException;
import com.skiply.student.exception.StudentAlreadyExistsException;
import com.skiply.student.exception.StudentNotFoundException;
import com.skiply.student.model.Student;
import com.skiply.student.repository.StudentRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Student student) {
        log.info("Creating student: {}", student);
        if (studentRepository.existsByStudentId(student.getStudentId())) {
            log.warn("Student creation failed - ID already exists: {}", student.getStudentId());
            throw new StudentAlreadyExistsException(student.getStudentId());
        }

        try {
            Student savedStudent = studentRepository.save(student);
            log.info("Created student: {}", savedStudent);
            return savedStudent;
        } catch (DataIntegrityViolationException e) {
            log.error("Failed to create student - data integrity violation: {}", e.getMessage());
            throw new InvalidStudentDataException("Invalid student data: " + e.getMessage());
        } catch (ConstraintViolationException e) {
            log.error("Failed to create student - constraint violation: {}", e.getMessage());
            throw new ConstraintViolationException(e.getConstraintViolations());
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String studentId) {
        log.debug("Fetching student with ID: {}", studentId);
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> {
                    log.warn("Student not found with ID: {}", studentId);
                    return new StudentNotFoundException(studentId);
                });
    }

    public boolean existsByStudentId(String studentId) {
        return studentRepository.existsByStudentId(studentId);
    }

    @Transactional
    public Student updateStudent(String studentId, Student studentDetails) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        if (null != studentDetails.getStudentId() && !studentId.equals(studentDetails.getStudentId())) {
            throw new InvalidStudentDataException("Student ID cannot be changed");
        }

        student.setName(studentDetails.getName());
        student.setGrade(studentDetails.getGrade());
        student.setMobileNumber(studentDetails.getMobileNumber());
        student.setSchoolName(studentDetails.getSchoolName());
        studentDetails.setEmailId(studentDetails.getEmailId());

        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidStudentDataException("Invalid student data: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteStudent(String studentId) {
        if (!studentRepository.existsByStudentId(studentId)) {
            throw new StudentNotFoundException(studentId);
        }
        studentRepository.deleteByStudentId(studentId);
    }

    public List<Student> findByGrade(String grade) {
        List<Student> students = studentRepository.findByGrade(grade);
        return students;
    }

    public List<Student> findBySchoolName(String schoolName) {
        log.debug("Fetching students from school: {}", schoolName);
        return studentRepository.findBySchoolName(schoolName);
    }

    public List<Student> findByMobileNumber(String mobileNumber) {
        log.debug("Fetching student with mobile number: {}", mobileNumber);
        return studentRepository.findByMobileNumber(mobileNumber);
    }
}