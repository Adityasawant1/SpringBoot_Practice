package com.spring.project.prod_ready_features.ProductionReady;

import com.spring.project.prod_ready_features.ProductionReady.clients.EmployeeClients;
import com.spring.project.prod_ready_features.ProductionReady.clients.impl.EmployeeClientImplementation;
import com.spring.project.prod_ready_features.ProductionReady.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductionReadyApplicationTests {

    @Autowired
    private EmployeeClients employeeClientImplementation;

    @Test
    @Order(3)
     void getAllEmployees()
    {
        List<EmployeeDTO> employeeDTOS = employeeClientImplementation.getAllEmployees();
        System.out.println(employeeDTOS);
    }



    @Test
    @Order(1)
    void createEmployee()
    {
        EmployeeDTO employeeDTO = new EmployeeDTO(null,"Aditya","sawantaditya3636@gmail.com",20,"USER",
                5000.00,
                LocalDate.of(2020,12,12),true);

        EmployeeDTO savedEmployee = employeeClientImplementation.createNewEmployee(employeeDTO);
        System.out.println(savedEmployee);
    }

    @Test
    @Order(2)
    void getEmployeeById()
    {
        EmployeeDTO employeeDTO = employeeClientImplementation.getEmployeeById(1L);
        System.out.println(employeeDTO);
    }
}
