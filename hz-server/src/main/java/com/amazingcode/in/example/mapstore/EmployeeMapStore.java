package com.amazingcode.in.example.mapstore;

import java.util.Collection;
import java.util.Map;

import com.amazingcode.in.example.entity.Employee;
import com.amazingcode.in.example.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.map.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapStore implements MapStore<Long, Employee> {

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Iterable<Long> loadAllKeys() {
        return null;
    }

    @Override
    public void delete(Long key) {

    }

    @Override
    public void deleteAll(Collection<Long> keys) {

    }

    @Override
    public Employee load(Long key) {
        return null;
    }

    @Override
    public Map<Long, Employee> loadAll(Collection<Long> keys) {
        return null;
    }

    @Override
    public void store(Long key, Employee value) {
        employeeRepository.save(value);
    }

    @Override
    public void storeAll(Map<Long, Employee> map) {

    }

}
