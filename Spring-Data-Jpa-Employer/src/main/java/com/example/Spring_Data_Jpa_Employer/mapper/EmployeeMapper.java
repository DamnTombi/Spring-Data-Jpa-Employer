package com.example.Spring_Data_Jpa_Employer.mapper;

import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoRequest;
import com.example.Spring_Data_Jpa_Employer.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee dtoCreateEntity(EmployeeDtoRequest request) {
        Employee employee = new Employee();

        Long id = request.id();
        if (id == null) employee.setId(id);
        String firstName = request.firstName();
        if (firstName != null && !firstName.isBlank()){ employee.setFirstName(firstName);}
        String lastName = request.lastName();
        if (lastName != null && !lastName.isBlank()){ employee.setLastName(lastName);}
        String position = request.position();
        if (position != null && !position.isBlank()){ employee.setPosition(position);}
        String phone = request.phone();
        if (phone != null && !phone.isBlank()){ employee.setPhone(phone);}
        return employee;
    }

    public Employee dtoUpdateToEntity(Long id, EmployeeDtoRequest request, Employee employeeToUpdate) {
        if (id != null) employeeToUpdate.setId(id);

        String firstName = request.firstName();
        if (firstName != null && !firstName.isBlank()){ employeeToUpdate.setFirstName(firstName);}
        String lastName = request.lastName();
        if (lastName != null && !lastName.isBlank()){ employeeToUpdate.setLastName(lastName);}
        String position = request.position();
        if (position != null && !position.isBlank()){ employeeToUpdate.setPosition(position);}
        String phone = request.phone();
        if (phone != null && !phone.isBlank()){ employeeToUpdate.setPhone(phone);}
        return employeeToUpdate;
    }
}
