package com.pro.EverestTechnologies.service;

import com.pro.EverestTechnologies.model.StudentMongo;

import java.util.List;
import java.util.Optional;

public interface StudentServiceMongo {
    StudentMongo saveStudent(StudentMongo student);                            // Create/Update a single student
    List<StudentMongo> saveStudents(List<StudentMongo> students);              // Create/Update multiple students (batch)
    List<StudentMongo> getAllStudents();                                       // Retrieve all students
    Optional<StudentMongo> getStudentById(String id);                          // Retrieve a student by ID
    StudentMongo updateStudent(String id, StudentMongo studentDetails);       // Update a student by ID
    void deleteStudent(String id);                                            // Delete a student by ID

}
