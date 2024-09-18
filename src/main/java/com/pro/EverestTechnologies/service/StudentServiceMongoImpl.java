package com.pro.EverestTechnologies.service;

import com.pro.EverestTechnologies.model.StudentMongo;
import com.pro.EverestTechnologies.repository.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceMongoImpl implements StudentServiceMongo {

    @Autowired
    private StudentMongoRepository studentMongoRepo;

    @Override
    public StudentMongo saveStudent(StudentMongo student) {
        return studentMongoRepo.save(student);
    }

    @Override
    public List<StudentMongo> saveStudents(List<StudentMongo> students) {
        return studentMongoRepo.saveAll(students);
    }

    @Override
    public List<StudentMongo> getAllStudents() {
        return studentMongoRepo.findAll();
    }

    @Override
    public Optional<StudentMongo> getStudentById(String id) {
        return studentMongoRepo.findById(id);
    }

    @Override
    public StudentMongo updateStudent(String id, StudentMongo studentDetails) {
        Optional<StudentMongo> existingStudent = studentMongoRepo.findById(id);
        if (existingStudent.isPresent()) {
            StudentMongo stu = existingStudent.get();
            stu.setName(studentDetails.getName());
            stu.setEmail(studentDetails.getEmail());
            stu.setAddress(studentDetails.getAddress());
            return studentMongoRepo.save(stu);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    @Override
    public void deleteStudent(String id) {
        Optional<StudentMongo> existingStudent = studentMongoRepo.findById(id);
        if (existingStudent.isPresent()) {
            studentMongoRepo.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }
}
