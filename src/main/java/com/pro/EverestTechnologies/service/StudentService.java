package com.pro.EverestTechnologies.service;

import com.pro.EverestTechnologies.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);                            // Create/Update a single student
    List<Student> saveStudents(List<Student> students);              // Create/Update multiple students (batch)
    List<Student> getAllStudents();                                  // Retrieve all students
    Optional<Student> getStudentById(Long id);                       // Retrieve a student by ID
    Student updateStudent(Long id, Student studentDetails);           // Update a student by ID
    void deleteStudent(Long id);                                     // Delete a student by ID




}
