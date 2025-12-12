package com.spring.project.homework_week3.controller;

import com.spring.project.homework_week3.entities.ProfessorEntity;
import com.spring.project.homework_week3.repository.ProfessorRepository;
import com.spring.project.homework_week3.services.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/add")
    public ResponseEntity<ProfessorEntity>addProfessor(@RequestBody ProfessorEntity professor)
    {
        return ResponseEntity.ok(professorService.addProfessor(professor));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfessorEntity>>getAll()
    {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }



}
