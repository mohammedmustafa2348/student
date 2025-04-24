//package com.example.student.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true)
//    private String studentId;
//
//    private String name;
//    private String grade;
//    private String mobileNumber;
//    private String schoolName;
//}

package com.skiply.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Student ID is required")
    @Size(min = 5, max = 20, message = "Student ID must be between 5 and 20 characters")
    private String studentId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must be 10-15 digits")
    private String mobileNumber;

    @NotBlank(message = "School name is required")
    private String schoolName;

    @NotBlank(message = "Email ID is required")
    @Email(message = "Email ID is invalid")
    private String emailId;
}