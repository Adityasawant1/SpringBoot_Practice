package com.SpringBoot.SpringLearningPhase.controllers;

import com.SpringBoot.SpringLearningPhase.dto.EmployeeDTO;
import com.SpringBoot.SpringLearningPhase.exception.ResourceNotfoundException;
import com.SpringBoot.SpringLearningPhase.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {

//    @GetMapping(path ="/")
//    public String hello()
//    {
//        return "Hello from Spring";
//    }
//
//    //Use of @PathVariable Passing Values Through URL
//    @GetMapping("/user/{id}/{name}")
//    public String give_Age(@PathVariable(required = false) int id,@PathVariable String name)
//    {
//        return "User Id : "+id+" & name : "+name;
//    }
//
//    //Use of @RequestParam Passing multile values from URL,,Seperated by ?
//    @GetMapping(path = "/user1")
//    public String user(@RequestParam(required = false) int id, @RequestParam(required = false) String name)
//    {
//        return "User ID : "+id+" Name : "+name;
//    }
//
//
//    @GetMapping("/Employees")
//    public EmployeeDetails Employee()
//    {
//        return new EmployeeDetails(1L,"Aditya",21,"Pune");
//    }
//
//
//    //@Post Request demos
//    @PostMapping("/post")
//    public String post(){
//        return "Hello from Post";
//    }
//
//    @PostMapping("/")
//    public EmployeeDetails detail(@RequestBody EmployeeDetails empID)
//    {
//        empID.setId(100L);
//        return empID;
//    }

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotfoundException("Employee Not Found with id : "+id)
        );
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false,name = "inputage") Long age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO employeeService1= employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(employeeService1, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId)
    {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId)
    {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody  Map<String , Object>updates,
                                             @PathVariable Long employeeId)
    {
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}
