package com.amazingcode.in.example.repository;

import com.amazingcode.in.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
