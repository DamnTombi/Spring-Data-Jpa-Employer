package com.example.Spring_Data_Jpa_Employer.service;

import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoRequest;
import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoResponse;
import com.example.Spring_Data_Jpa_Employer.entity.Employee;
import com.example.Spring_Data_Jpa_Employer.mapper.EmployeeMapper;
import com.example.Spring_Data_Jpa_Employer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public EmployeeDtoResponse create(EmployeeDtoRequest request) {
        try {
            Employee employee = repository.saveAndFlush(mapper.dtoCreateEntity(request));
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_CREATE_MSG.getMessage())
                    .employee(employee).build();
        }catch (RuntimeException e){
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NO_CONTENT.value())
                    .reasonPhase(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_CREATE_MSG.getMessage()
                    +". "+e.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse getAll() {
        List<Employee> list = repository.getAllEmployees().orElse(Collections.emptyList());
        if (!list.isEmpty()){
        return EmployeeDtoResponse.builder()
                .status(HttpStatus.OK.value())
                .reasonPhase(HttpStatus.OK.getReasonPhrase())
                .message(EmployeeDtoResponse.Message.SUCCESS_GET_LIST_MSG.getMessage())
                .employees(list).build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_LIST_MSG.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse getById(Long id) {
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .employee(optional.get())
                    .build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse findByPosition(String position) {
        List<Employee> list = repository.findByPosition(position).orElse(Collections.emptyList());
        if (!list.isEmpty()) {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_GET_BY_POSITION_MSG.getMessage())
                    .employees(list)
                    .build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_BY_POSITION_MSG.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse findByLastName(String lastName) {
        List<Employee> list = repository.findByLastName(lastName).orElse(Collections.emptyList());
        if (!list.isEmpty()) {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_GET_BY_LAST_NAME_MSG.getMessage())
                    .employees(list)
                    .build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_BY_LAST_NAME_MSG.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse updateById(Long id, EmployeeDtoRequest request) {
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            Employee employeeToUpdate = mapper.dtoUpdateToEntity(id,request,optional.get());
            repository.saveAndFlush(employeeToUpdate);
        }Employee employee = repository.findById(id).orElse(null);
        if (employee != null) {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .employee(employee)
                    .build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
    }

    @Override
    @Transactional
    public EmployeeDtoResponse deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }else {
            return EmployeeDtoResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse.Message.FAILURE_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }

    }
}
