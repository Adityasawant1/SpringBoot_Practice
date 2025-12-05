package com.SpringBoot.SpringLearningPhase.services;


import java.util.List;

import com.SpringBoot.SpringLearningPhase.entities.Student;
import com.SpringBoot.SpringLearningPhase.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
