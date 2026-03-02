package com.example.Spring_Data_Jpa_Employer.repository;

import com.example.Spring_Data_Jpa_Employer.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Qualifier("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e ORDER BY e.id ASC")
    Optional<List<Employee>> getAllEmployees();
    Optional<List<Employee>> findByPosition(String position);
    Optional<List<Employee>> findByLastName(String lastName);
    Optional<Employee> findById(Long id);
    Optional<Employee> deleteById(Long id);
}
