package com.example.Spring_Data_Jpa_Employer.controller;

import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoRequest;
import com.example.Spring_Data_Jpa_Employer.dto.EmployeeDtoResponse;
import com.example.Spring_Data_Jpa_Employer.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoResponse> createEmployee(@RequestBody EmployeeDtoRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<EmployeeDtoResponse> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping("/by-position")
    public ResponseEntity<EmployeeDtoResponse> getByPosition(@RequestParam("position") String position){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByPosition(position));
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<EmployeeDtoResponse> getByLastName(@RequestParam("lastName") String lastName){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByLastName(lastName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDtoRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateById(id, request));
    }

    @DeleteMapping
    public ResponseEntity<EmployeeDtoResponse> deleteEmployee(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id));
    }
}
