package com.spring.project.homework_week3.controller;

import com.spring.project.homework_week3.entities.SubjectEntity;
import com.spring.project.homework_week3.repository.ProfessorRepository;
import com.spring.project.homework_week3.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    // Add Subject with Professor ID
    @PostMapping("/add")
    public SubjectEntity addSubject(@RequestParam Long professorId, @RequestBody SubjectEntity subject) {
        subject.setProfessor(professorRepository.findById(professorId).orElseThrow());
        return subjectRepository.save(subject);
    }

    @GetMapping("/all")
    public List<SubjectEntity> getAll(){
        return subjectRepository.findAll();
    }
}
