package com.spring.project.homework_week3.services;

import com.spring.project.homework_week3.entities.ProfessorEntity;
import com.spring.project.homework_week3.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorEntity addProfessor(ProfessorEntity professor) {
        return professorRepository.save(professor);
    }

    public List<ProfessorEntity> getAllProfessors() {
        return professorRepository.findAll();
    }
}
