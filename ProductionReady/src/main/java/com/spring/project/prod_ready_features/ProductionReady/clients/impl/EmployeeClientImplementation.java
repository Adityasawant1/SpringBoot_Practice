package com.spring.project.prod_ready_features.ProductionReady.clients.impl;

import com.spring.project.prod_ready_features.ProductionReady.advice.ApiResponse;
import com.spring.project.prod_ready_features.ProductionReady.clients.EmployeeClients;
import com.spring.project.prod_ready_features.ProductionReady.dto.EmployeeDTO;
import com.spring.project.prod_ready_features.ProductionReady.exception.ResourceNotfoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeClientImplementation implements EmployeeClients {


    private final RestClient restClient;


    Logger log = LoggerFactory.getLogger(EmployeeClientImplementation.class);



    @Override
    public List<EmployeeDTO> getAllEmployees() {



        log.trace("Trying to retrieve all the Employees");
        try {
            log.debug("Successfully retrieved the employee in getElementById");

                return restClient.get()
                        .uri("employees")
                        .retrieve()
                        .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                            log.error(new String(res.getBody().readAllBytes()));
                            throw new ResourceNotfoundException("Could not get the employee");
                        })
                        .body(new ParameterizedTypeReference<List<EmployeeDTO>>() {});


        }catch (Exception e)
        {
            log.error("Exception occured in getElement By Id");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            return restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<EmployeeDTO>() {
                    });
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            return restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(res,req)->{
                        System.out.println(new String(req.getBody().readAllBytes()));
                        throw new ResourceNotfoundException("Could now created employee");
                    })
                    .body(new ParameterizedTypeReference<EmployeeDTO>() {
                    });
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
