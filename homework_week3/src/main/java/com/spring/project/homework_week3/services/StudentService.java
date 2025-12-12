package com.spring.project.homework_week3.services;

import com.spring.project.homework_week3.dto.StudentDTO;
import com.spring.project.homework_week3.entities.AdmissonEntity;
import com.spring.project.homework_week3.entities.ProfessorEntity;
import com.spring.project.homework_week3.entities.StudentEntity;
import com.spring.project.homework_week3.entities.SubjectEntity;
import com.spring.project.homework_week3.exception.ResourceNotfoundException;
import com.spring.project.homework_week3.repository.AdmissonRepository;
import com.spring.project.homework_week3.repository.ProfessorRepository;
import com.spring.project.homework_week3.repository.StudentRepository;
import com.spring.project.homework_week3.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final AdmissonRepository admissonRepository;

    private final ModelMapper mapper;


    public StudentDTO saveStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = mapper.map(studentDTO,StudentEntity.class);

        if(studentDTO.getProfessorIds()!=null && !studentDTO.getProfessorIds().isEmpty())
        {
            studentEntity.setProfessors(professorRepository.findAllById(studentDTO.getProfessorIds()));
        }

        if(studentDTO.getSubjectIds()!= null)
        {
            studentEntity.setSubjects(subjectRepository.findAllById(studentDTO.getSubjectIds()));
        }

        StudentEntity saved = studentRepository.save(studentEntity);

    if(studentDTO.getAdmissionFees()!=null)
    {
        AdmissonEntity admission = AdmissonEntity.builder()
                .fees(studentDTO.getAdmissionFees())
                .student(saved)
                .build();
        admissonRepository.save(admission);
        saved.setAdmissionRecord(admission);
    }
    return convertToDTO(saved);
    }

    private StudentDTO convertToDTO(StudentEntity saved) {
        StudentDTO dto = mapper.map(saved,StudentDTO.class);

        if(saved.getProfessors()!=null)
        {
            dto.setProfessorNames(
                    saved.getProfessors().stream()
                            .map(ProfessorEntity::getName)
                            .toList()
            );
        }

        if(saved.getSubjects()!=null)
        {
            dto.setSubjectNames(
                    saved.getSubjects().stream()
                            .map(SubjectEntity::getTitle)
                            .toList()
            );
        }

        if(saved.getAdmissionRecord()!=null)
        {
            dto.setAdmissionFees(saved.getAdmissionRecord().getFees());
        }
        return dto;
    }


    public Optional<StudentDTO> getStudentById(Long id) {

        return studentRepository
                .findById(id)
                .map(this::convertToDTO);
    }

    public StudentDTO assignProfessorsToStudent(Long studentId, List<Long> professorIds) {

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotfoundException("Student not found with id: " + studentId));

        // Fetch professors
        List<ProfessorEntity> professors = professorRepository.findAllById(professorIds);

        // Set or append professors
        student.setProfessors(professors);

        StudentEntity saved = studentRepository.save(student);
        return convertToDTO(saved);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public StudentDTO assignSubjectsToStudent(Long studentId, List<Long> subjectIds) {

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotfoundException("Student not found with id: " + studentId));

        List<SubjectEntity> subjects = subjectRepository.findAllById(subjectIds);

        student.setSubjects(subjects);

        StudentEntity saved = studentRepository.save(student);
        return convertToDTO(saved);
    }


    public StudentDTO assignAdmissionToStudent(Long studentId, Integer fees) {

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotfoundException("Student not found with id: " + studentId));

        AdmissonEntity admission = AdmissonEntity.builder()
                .fees(fees)
                .student(student)
                .build();

        admissonRepository.save(admission);
        student.setAdmissionRecord(admission);

        return convertToDTO(studentRepository.save(student));
    }

}
