package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.dto.EmployeeDto;
import com.amazingcode.in.example.entity.Employee;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private IMap<Long, Employee> getEmployeeMap(){
        return hazelcastInstance.getMap("employeeMap");
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        logger.info("Received request to create employee: {}", employeeDto);

        IMap<Long, Employee> employeeMap = getEmployeeMap();

        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());

        employeeMap.set(employee.getId(), employee);
        logger.info("Stored employee in Hazelcast map with ID: {}", employee.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        IMap<Long, Employee> employeeMap = getEmployeeMap();
        List<Employee> employees = new ArrayList<>(employeeMap.values());

        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for(Employee employee: employees){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setDepartment(employee.getDepartment());

            employeeDtoList.add(employeeDto);
        }

        return ResponseEntity.ok(employeeDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        logger.info("Received request to fetch employee with ID: {}", id);

        IMap<Long, Employee> employeeMap = getEmployeeMap();
        Employee employee = employeeMap.get(id);

        if (employee == null) {
            logger.warn("Employee with ID {} not found in Hazelcast map", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setDepartment(employee.getDepartment());

        logger.info("Fetched employee from Hazelcast map: {}", employeeDto);
        return ResponseEntity.ok(employeeDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        logger.info("Received request to update employee with ID: {}", id);

        IMap<Long, Employee> employeeMap = getEmployeeMap();

        if (!employeeMap.containsKey(id)) {
            logger.warn("Employee with ID {} not found in Hazelcast map", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Employee existingEmployee = employeeMap.get(id);
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setDepartment(employeeDto.getDepartment());

        employeeMap.set(id, existingEmployee);
        logger.info("Updated employee in Hazelcast map with ID: {}", id);

        EmployeeDto updatedDto = new EmployeeDto();
        updatedDto.setId(existingEmployee.getId());
        updatedDto.setName(existingEmployee.getName());
        updatedDto.setDepartment(existingEmployee.getDepartment());

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("Received request to delete employee with ID: {}", id);

        IMap<Long, Employee> employeeMap = getEmployeeMap();

        if (!employeeMap.containsKey(id)) {
            logger.warn("Employee with ID {} not found in Hazelcast map", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        employeeMap.delete(id);
        logger.info("Deleted employee from Hazelcast map with ID: {}", id);

        return ResponseEntity.noContent().build();
    }

}
