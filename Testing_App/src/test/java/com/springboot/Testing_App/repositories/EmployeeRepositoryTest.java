package com.springboot.Testing_App.repositories;

import com.springboot.Testing_App.TestContainerConfiguration;
import com.springboot.Testing_App.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.List;

@Import(TestContainerConfiguration.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private  Employee employee;
    @BeforeEach
    void setEmployee()
    {
         employee = Employee.builder()
                .name("Aditya")
                .email("sawantaditya3636@gmail.com")
                .salary(20000L)
                .build();

    }
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee()
    {
        //Given
        employeeRepository.save(employee);

        //When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        //Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());


    }

    @Test
    void testFindByEmail_whenEmailIsNotValid_thenReturnEmptyEmployeeList()
    {
        //Given
        String email = "notPresent.123@gmail.com";


        //When
        List<Employee>employeeList= employeeRepository.findByEmail(email);

        //Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();


    }
}
