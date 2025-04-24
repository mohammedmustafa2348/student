package com.skiply.student.service;

import com.skiply.student.exception.*;
import com.skiply.student.model.Student;
import com.skiply.student.repository.StudentRepository;
import com.skiply.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private Student updatedStudent;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setStudentId("STU001");
        student.setName("John Doe");
        student.setGrade("10");
        student.setMobileNumber("1234567890");
        student.setSchoolName("Skiply School");

        updatedStudent = new Student();
        updatedStudent.setStudentId("STU001");
        updatedStudent.setName("John Doe Updated");
        updatedStudent.setGrade("11");
        updatedStudent.setMobileNumber("0987654321");
        updatedStudent.setSchoolName("Updated School");
    }

    @Test
    void createStudent_ShouldReturnSavedStudent_WhenSuccessful() {
        when(studentRepository.existsByStudentId(student.getStudentId())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentService.createStudent(student);

        assertNotNull(savedStudent);
        assertEquals(student.getName(), savedStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void createStudent_ShouldThrowException_WhenStudentIdExists() {
        when(studentRepository.existsByStudentId(student.getStudentId())).thenReturn(true);

        assertThrows(StudentAlreadyExistsException.class, () -> {
            studentService.createStudent(student);
        });

        verify(studentRepository, never()).save(any());
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        List<Student> students = studentService.getAllStudents();

        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_ShouldReturnStudent_WhenExists() {
        when(studentRepository.findByStudentId(student.getStudentId()))
                .thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(student.getStudentId());

        assertNotNull(foundStudent);
        assertEquals(student.getName(), foundStudent.getName());
        verify(studentRepository, times(1)).findByStudentId(student.getStudentId());
    }

    @Test
    void getStudentById_ShouldThrowException_WhenNotFound() {
        when(studentRepository.findByStudentId("NON_EXISTENT"))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById("NON_EXISTENT");
        });
    }

    @Test
    void updateStudent_ShouldReturnUpdatedStudent_WhenSuccessful() {
        when(studentRepository.findByStudentId(student.getStudentId()))
                .thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(student.getStudentId(), updatedStudent);

        assertNotNull(result);
        assertEquals(updatedStudent.getName(), result.getName());
        assertEquals(updatedStudent.getGrade(), result.getGrade());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void updateStudent_ShouldThrowException_WhenStudentNotFound() {
        when(studentRepository.findByStudentId("NON_EXISTENT"))
                .thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.updateStudent("NON_EXISTENT", updatedStudent);
        });

        verify(studentRepository, never()).save(any());
    }

    @Test
    void updateStudent_ShouldThrowException_WhenIdsDontMatch() {
        when(studentRepository.findByStudentId(student.getStudentId()))
                .thenReturn(Optional.of(student));
        Student wrongStudent = new Student();
        wrongStudent.setStudentId("WRONG_ID");

        assertThrows(InvalidStudentDataException.class, () -> {
            studentService.updateStudent(student.getStudentId(), wrongStudent);
        });

        verify(studentRepository, never()).save(any());
    }

    @Test
    void deleteStudent_ShouldDeleteStudent_WhenExists() {
        when(studentRepository.existsByStudentId(student.getStudentId())).thenReturn(true);
        doNothing().when(studentRepository).deleteByStudentId(student.getStudentId());

        assertDoesNotThrow(() -> {
            studentService.deleteStudent(student.getStudentId());
        });

        verify(studentRepository, times(1)).deleteByStudentId(student.getStudentId());
    }

    @Test
    void deleteStudent_ShouldThrowException_WhenNotFound() {
        when(studentRepository.existsByStudentId("NON_EXISTENT")).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.deleteStudent("NON_EXISTENT");
        });

        verify(studentRepository, never()).deleteByStudentId(any());
    }

    @Test
    void createStudent_ShouldThrowException_WhenDataIsInvalid() {
        Student invalidStudent = new Student(); // Missing required fields
        when(studentRepository.existsByStudentId(any())).thenReturn(false);
        when(studentRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(InvalidStudentDataException.class, () -> {
            studentService.createStudent(invalidStudent);
        });
    }

    @Test
    void updateStudent_ShouldThrowException_WhenDataIsInvalid() {
        when(studentRepository.findByStudentId(student.getStudentId()))
                .thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(InvalidStudentDataException.class, () -> {
            studentService.updateStudent(student.getStudentId(), updatedStudent);
        });
    }

    @Test
    void existsByStudentId_ShouldReturnTrue_WhenStudentExists() {
        when(studentRepository.existsByStudentId(student.getStudentId())).thenReturn(true);

        assertTrue(studentService.existsByStudentId(student.getStudentId()));
    }

    @Test
    void existsByStudentId_ShouldReturnFalse_WhenStudentDoesNotExist() {
        when(studentRepository.existsByStudentId("NON_EXISTENT")).thenReturn(false);

        assertFalse(studentService.existsByStudentId("NON_EXISTENT"));
    }

}