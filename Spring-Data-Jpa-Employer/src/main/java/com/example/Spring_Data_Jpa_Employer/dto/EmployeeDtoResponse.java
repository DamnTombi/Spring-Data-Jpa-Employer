package com.example.Spring_Data_Jpa_Employer.dto;


import com.example.Spring_Data_Jpa_Employer.entity.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDtoResponse {
    private int status;
    private String reasonPhase;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Employee employee;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Employee> employees;

    @Getter
    public enum Message {
        SUCCESS_CREATE_MSG("Employee has been created successfully."),
        FAILURE_CREATE_MSG("Employee has not been created!"),
        SUCCESS_GET_LIST_MSG("Employee list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Employee list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Employee with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Employee with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Employee with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Employee with id %s has been deleted successfully."),
        SUCCESS_GET_BY_POSITION_MSG("Employee list has been fetched by position successfully."),
        FAILURE_GET_BY_POSITION_MSG("Employee list has not been found by position!"),
        SUCCESS_GET_BY_LAST_NAME_MSG("Employee list has been fetched by last name successfully."),
        FAILURE_GET_BY_LAST_NAME_MSG("Employee list has not been found by last name.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
