package com.spring.week2.homework_week2.controller;

import com.spring.week2.homework_week2.dto.DepartmentDTO;
import com.spring.week2.homework_week2.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Departments")
public class DepartmentController {


    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>>getAllDepartments()
    {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping(path="/{deptId}")
    public ResponseEntity<DepartmentDTO>getDeptById(@PathVariable(name = "deptId") Long id)
    {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDeptById(id);
        return departmentDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<DepartmentDTO>saveDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO departmentDTO1=departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDTO1, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{deptId}")
    public ResponseEntity<DepartmentDTO>updateDept(@PathVariable(name = "deptId") Long id,@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO departmentDTO1=departmentService.updateDept(id,departmentDTO);
        return ResponseEntity.ok(departmentDTO1);
    }



    @DeleteMapping(path = "/{deptId}")
    public ResponseEntity<Boolean>deleteDeptById(@PathVariable(name = "deptId") Long id)
    {
         boolean gotDeleted =departmentService.deleteDeptById(id);
         if(gotDeleted) return ResponseEntity.ok(true);
         return ResponseEntity.notFound().build();

    }
}
