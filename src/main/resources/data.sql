-- Drop existing table if exists (H2 syntax)
DROP TABLE IF EXISTS student;

-- Create the student table with H2-specific syntax
CREATE TABLE IF NOT EXISTS student (
                                       id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                       student_id VARCHAR(20) NOT NULL,
                                       name VARCHAR(50) NOT NULL,
                                       grade VARCHAR(255) NOT NULL,
                                       mobile_number VARCHAR(15) NOT NULL,
                                       school_name VARCHAR(255) NOT NULL,
                                       email_id VARCHAR(255) NOT NULL,
                                       CONSTRAINT uk_student_id UNIQUE (student_id)
);

-- Insert sample student data
INSERT INTO student (student_id, name, grade, mobile_number, school_name, email_id) VALUES
-- Primary School Students
('STD001', 'Ahmed Khan', 'Grade 3', '0501234567', 'Dubai International School', 'ahmed.khan@example.com'),
('STD002', 'Sara Ali', 'Grade 4', '0512345678', 'Dubai International School', 'sara.ali@example.com'),
('STD003', 'Mohammad Rahman', 'Grade 5', '0523456789', 'Al Wasl School', 'mohammad.r@example.com'),

-- Middle School Students
('STD004', 'Fatima Hassan', 'Grade 6', '0534567890', 'Al Wasl School', 'fatima.h@example.com'),
('STD005', 'Omar Sajid', 'Grade 7', '0545678901', 'Dubai Modern School', 'omar.s@example.com'),
('STD006', 'Aisha Malik', 'Grade 8', '0556789012', 'Dubai Modern School', 'aisha.m@example.com'),

-- High School Students
('STD007', 'Zainab Ahmad', 'Grade 9', '0567890123', 'Emirates International School', 'zainab.a@example.com'),
('STD008', 'Yusuf Ali', 'Grade 10', '0578901234', 'Emirates International School', 'yusuf.ali@example.com'),
('STD009', 'Layla Mohammed', 'Grade 11', '0589012345', 'Dubai National School', 'layla.m@example.com'),
('STD010', 'Ibrahim Khan', 'Grade 12', '0590123456', 'Dubai National School', 'ibrahim.k@example.com');

-- Create indexes (H2 syntax)
CREATE INDEX IF NOT EXISTS idx_student_name ON student(name);
CREATE INDEX IF NOT EXISTS idx_student_grade ON student(grade);
CREATE INDEX IF NOT EXISTS idx_student_school ON student(school_name);-- Drop existing table if exists
DROP TABLE IF EXISTS student;

-- Create the student table
CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    grade VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    school_name VARCHAR(255) NOT NULL,
    email_id VARCHAR(255) NOT NULL
);

-- Insert sample student data
INSERT INTO student (
    student_id,
    name,
    grade,
    mobile_number,
    school_name,
    email_id
) VALUES 
-- Primary School Students
('STD001', 'Ahmed Khan', 'Grade 3', '0501234567', 'Dubai International School', 'ahmed.khan@example.com'),
('STD002', 'Sara Ali', 'Grade 4', '0512345678', 'Dubai International School', 'sara.ali@example.com'),
('STD003', 'Mohammad Rahman', 'Grade 5', '0523456789', 'Al Wasl School', 'mohammad.r@example.com'),

-- Middle School Students
('STD004', 'Fatima Hassan', 'Grade 6', '0534567890', 'Al Wasl School', 'fatima.h@example.com'),
('STD005', 'Omar Sajid', 'Grade 7', '0545678901', 'Dubai Modern School', 'omar.s@example.com'),
('STD006', 'Aisha Malik', 'Grade 8', '0556789012', 'Dubai Modern School', 'aisha.m@example.com'),

-- High School Students
('STD007', 'Zainab Ahmad', 'Grade 9', '0567890123', 'Emirates International School', 'zainab.a@example.com'),
('STD008', 'Yusuf Ali', 'Grade 10', '0578901234', 'Emirates International School', 'yusuf.ali@example.com'),
('STD009', 'Layla Mohammed', 'Grade 11', '0589012345', 'Dubai National School', 'layla.m@example.com'),
('STD010', 'Ibrahim Khan', 'Grade 12', '0590123456', 'Dubai National School', 'ibrahim.k@example.com'),

-- Additional Students with Different Grades
('STD011', 'Noor Hamid', 'Grade 1', '0501112233', 'Little Flowers School', 'noor.h@example.com'),
('STD012', 'Hassan Ali', 'Grade 2', '0502223344', 'Little Flowers School', 'hassan.ali@example.com'),
('STD013', 'Amira Syed', 'Grade 5', '0503334455', 'Al Wasl School', 'amira.s@example.com'),
('STD014', 'Karim Raza', 'Grade 8', '0504445566', 'Dubai Modern School', 'karim.r@example.com'),
('STD015', 'Sofia Khan', 'Grade 10', '0505556677', 'Emirates International School', 'sofia.k@example.com');

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_student_name ON student(name);
CREATE INDEX IF NOT EXISTS idx_student_grade ON student(grade);
CREATE INDEX IF NOT EXISTS idx_student_school ON student(school_name);