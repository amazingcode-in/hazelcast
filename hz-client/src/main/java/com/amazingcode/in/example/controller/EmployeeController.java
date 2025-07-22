package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.dto.EmployeeDto;
import com.amazingcode.in.example.entity.Employee;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        IMap<Long, Employee> employeeMap = hazelcastInstance.getMap("employeeMap");

        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());

        employeeMap.put(employee.getId(), employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
    }

}
