package com.spring.project.prod_ready_features.ProductionReady.clients;


import com.spring.project.prod_ready_features.ProductionReady.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClients {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
