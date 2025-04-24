package com.skiply.student.repository;

import com.skiply.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by studentId (unique field)
    Optional<Student> findByStudentId(String studentId);

    // Check if a student exists by studentId
    boolean existsByStudentId(String studentId);

    // Delete student by studentId
    void deleteByStudentId(String studentId);

    // Custom query to find students by school name
    List<Student> findBySchoolName(String schoolName);

    // Custom query to find students by grade
     List<Student> findByGrade(String grade);

    // Custom query to find students by grade
     List<Student> findByMobileNumber(String mobileNumber);
}
