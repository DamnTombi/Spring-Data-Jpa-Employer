package com.example.Spring_Data_Jpa_Employer.service;

import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoRequest;
import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDtoResponse create(EmployeeDtoRequest request);
    EmployeeDtoResponse getAll();
    EmployeeDtoResponse getById(Long id);
    EmployeeDtoResponse findByPosition(String position);
    EmployeeDtoResponse findByLastName(String lastName);
    EmployeeDtoResponse updateById(Long id, EmployeeDtoRequest request);
    EmployeeDtoResponse deleteById(Long id);

}
