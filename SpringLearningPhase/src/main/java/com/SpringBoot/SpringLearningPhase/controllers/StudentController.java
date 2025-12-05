package com.SpringBoot.SpringLearningPhase.controllers;

import java.util.List;

import com.SpringBoot.SpringLearningPhase.entities.Student;
import com.SpringBoot.SpringLearningPhase.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getAllStudents();
    }
}