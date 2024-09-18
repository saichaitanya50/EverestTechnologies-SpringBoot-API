package com.pro.EverestTechnologies.controller;

import com.pro.EverestTechnologies.dto.ResponseMessage;
import com.pro.EverestTechnologies.model.Student;
import com.pro.EverestTechnologies.model.StudentMongo;
import com.pro.EverestTechnologies.service.StudentService;
import com.pro.EverestTechnologies.service.StudentServiceMongo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentServiceMongo studentServiceMongo;

    @GetMapping("/csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    // SQL Endpoints
    @PostMapping("/sql")
    public ResponseEntity<ResponseMessage> saveStudentToSql(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>(new ResponseMessage("Student is added to SQL"), HttpStatus.CREATED);
    }

    @PostMapping("/sql/batchIntake")
    public ResponseEntity<ResponseMessage> saveStudentsToSql(@RequestBody List<Student> students) {
        studentService.saveStudents(students);
        return new ResponseEntity<>(new ResponseMessage("Students have been added to SQL"), HttpStatus.CREATED);
    }

    @GetMapping("/sql")
    public ResponseEntity<List<Student>> getAllStudentsFromSql() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/sql/{id}")
    public ResponseEntity<Object> getStudentByIdFromSql(@PathVariable long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Student not found with ID " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/sql/{id}")
    public ResponseEntity<ResponseMessage> updateStudentByIdInSql(@PathVariable long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return new ResponseEntity<>(new ResponseMessage("Student is updated in SQL"), HttpStatus.OK);
    }

    @DeleteMapping("/sql/{id}")
    public ResponseEntity<ResponseMessage> deleteStudentByIdFromSql(@PathVariable long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(new ResponseMessage("Student is deleted from SQL"), HttpStatus.NO_CONTENT);
    }

    // MongoDB Endpoints
    @PostMapping("/nosql")
    public ResponseEntity<ResponseMessage> saveStudentToMongo(@RequestBody StudentMongo student) {
        studentServiceMongo.saveStudent(student);
        return new ResponseEntity<>(new ResponseMessage("Student is added to MongoDB"), HttpStatus.CREATED);
    }

    @PostMapping("/nosql/batchIntake")
    public ResponseEntity<ResponseMessage> saveStudentsToMongo(@RequestBody List<StudentMongo> students) {
        studentServiceMongo.saveStudents(students);
        return new ResponseEntity<>(new ResponseMessage("Students have been added to MongoDB"), HttpStatus.CREATED);
    }

    @GetMapping("/nosql")
    public ResponseEntity<List<StudentMongo>> getAllStudentsFromMongo() {
        List<StudentMongo> students = studentServiceMongo.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/nosql/{id}")
    public ResponseEntity<Object> getStudentByIdFromMongo(@PathVariable String id) {
        Optional<StudentMongo> student = studentServiceMongo.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Student not found with ID " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/nosql/{id}")
    public ResponseEntity<ResponseMessage> updateStudentByIdInMongo(@PathVariable String id, @RequestBody StudentMongo student) {
        studentServiceMongo.updateStudent(id, student);
        return new ResponseEntity<>(new ResponseMessage("Student is updated in MongoDB"), HttpStatus.OK);
    }

    @DeleteMapping("/nosql/{id}")
    public ResponseEntity<ResponseMessage> deleteStudentByIdFromMongo(@PathVariable String id) {
        studentServiceMongo.deleteStudent(id);
        return new ResponseEntity<>(new ResponseMessage("Student is deleted from MongoDB"), HttpStatus.NO_CONTENT);
    }

    // Both SQL and MongoDB Endpoints
    @PostMapping("/alldb")
    public ResponseEntity<ResponseMessage> saveStudentToBoth(@RequestBody Student student) {
        studentService.saveStudent(student);
        StudentMongo studentMongo = new StudentMongo(student.getId().toString(), student.getName(), student.getEmail(), student.getAddress());
        studentServiceMongo.saveStudent(studentMongo);
        return new ResponseEntity<>(new ResponseMessage("Student is added to both SQL and MongoDB"), HttpStatus.CREATED);
    }

    @PostMapping("/alldb/batchIntake")
    public ResponseEntity<ResponseMessage> saveStudentsToBoth(@RequestBody List<Student> students) {
        studentService.saveStudents(students);
        List<StudentMongo> studentMongoList = students.stream()
                .map(stu -> new StudentMongo(stu.getId().toString(), stu.getName(), stu.getEmail(), stu.getAddress()))
                .toList();
        studentServiceMongo.saveStudents(studentMongoList);
        return new ResponseEntity<>(new ResponseMessage("Students have been added to both SQL and MongoDB"), HttpStatus.CREATED);
    }

    @GetMapping("/alldb")
    public ResponseEntity<List<Student>> getAllStudentsFromBoth() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/alldb/{id}")
    public ResponseEntity<Object> getStudentByIdFromBoth(@PathVariable long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            StudentMongo studentMongo = studentServiceMongo.getStudentById(String.valueOf(id)).orElse(null);
            if (studentMongo != null) {
                return new ResponseEntity<>(List.of(student.get(), studentMongo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Student found in SQL but not in MongoDB"), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Student not found with ID " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/alldb/{id}")
    public ResponseEntity<ResponseMessage> updateStudentByIdInBoth(@PathVariable long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        StudentMongo studentMongo = new StudentMongo(String.valueOf(id), student.getName(), student.getEmail(), student.getAddress());
        studentServiceMongo.updateStudent(String.valueOf(id), studentMongo);
        return new ResponseEntity<>(new ResponseMessage("Student is updated in both SQL and MongoDB"), HttpStatus.OK);
    }

    @DeleteMapping("/alldb/{id}")
    public ResponseEntity<ResponseMessage> deleteStudentByIdFromBoth(@PathVariable long id) {
        studentService.deleteStudent(id);
        studentServiceMongo.deleteStudent(String.valueOf(id));
        return new ResponseEntity<>(new ResponseMessage("Student is deleted from both SQL and MongoDB"), HttpStatus.NO_CONTENT);
    }
}
