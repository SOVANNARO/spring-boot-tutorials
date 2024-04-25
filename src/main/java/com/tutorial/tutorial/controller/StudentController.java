package com.tutorial.tutorial.controller;

import com.tutorial.tutorial.entitiy.Student;
import com.tutorial.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/student/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        return student.get();
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Student existingStudent = studentOptional.get();
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());

        return studentRepository.save(existingStudent);
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Student existingStudent = studentOptional.get();
        studentRepository.delete(existingStudent);
        return ResponseEntity.status(HttpStatus.OK).body("Student with ID " + id + " deleted successfully");
    }
}
