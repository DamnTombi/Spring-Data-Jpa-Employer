package com.example.Spring_Data_Jpa_Employer.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmployeeDtoRequest (
    Long id,
    String firstName,
    String lastName,
    String position,
    String phone
){}

