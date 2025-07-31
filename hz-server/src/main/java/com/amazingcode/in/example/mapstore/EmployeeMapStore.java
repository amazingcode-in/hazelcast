package com.amazingcode.in.example.mapstore;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.map.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EmployeeMapStore implements MapStore<Long, Employee> {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMapStore.class);

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Iterable<Long> loadAllKeys() {
        logger.info("Loading all keys from Employee repository");
        return employeeRepository.findAll()
                .stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long key) {
        logger.info("Deleting Employee with ID: {}", key);
        employeeRepository.deleteById(key);
    }

    @Override
    public void deleteAll(Collection<Long> keys) {
        logger.info("Deleting Employees with IDs: {}", keys);
        employeeRepository.deleteAllById(keys);
    }

    @Override
    public Employee load(Long key) {
        logger.info("Loading Employee with ID: {}", key);
        return employeeRepository.findById(key).orElse(null);
    }

    @Override
    public Map<Long, Employee> loadAll(Collection<Long> keys) {
        logger.info("Loading Employees with IDs: {}", keys);
        return employeeRepository.findAllById(keys)
                .stream()
                .collect(Collectors.toMap(Employee::getId, emp -> emp));
    }

    @Override
    public void store(Long key, Employee value) {
        logger.info("Storing Employee with ID: {}", key);
        employeeRepository.save(value);
    }

    @Override
    public void storeAll(Map<Long, Employee> map) {
        logger.info("Storing multiple Employees: {}", map.keySet());
        employeeRepository.saveAll(map.values());
    }

}
