package com.spring.project.homework_week3.controller;

import com.spring.project.homework_week3.dto.StudentDTO;
import com.spring.project.homework_week3.exception.ResourceNotfoundException;
import com.spring.project.homework_week3.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable(name = "studentId") Long id)
    {
        Optional<StudentDTO> studentDTO = studentService.getStudentById(id);
        return studentDTO
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotfoundException("Student not found with id : "+id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent()
    {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PostMapping("/add")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO)
    {
        return new ResponseEntity<>(studentService.saveStudent(studentDTO), HttpStatus.CREATED);
    }

    //PUT /students/1/professors   json row - [1, 2, 5]
    @PutMapping("/{id}/professors")
    public ResponseEntity<StudentDTO> assignProfessors(
            @PathVariable Long id,
            @RequestBody List<Long> professorIds) {

        return ResponseEntity.ok(studentService.assignProfessorsToStudent(id, professorIds));
    }
//PUT /students/1/subjects  json row = [3, 4]
    @PutMapping("/{id}/subjects")
    public ResponseEntity<StudentDTO> assignSubjects(
            @PathVariable Long id,
            @RequestBody List<Long> subjectIds) {

        return ResponseEntity.ok(studentService.assignSubjectsToStudent(id, subjectIds));
    }


    @PutMapping("/{id}/admission")
    public ResponseEntity<StudentDTO> assignAdmission(
            @PathVariable Long id,
            @RequestBody Integer fees) {

        return ResponseEntity.ok(studentService.assignAdmissionToStudent(id, fees));
    }


}
