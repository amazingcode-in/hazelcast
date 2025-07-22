package com.amazingcode.in.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    private Long id;
    private String name;
    private String department;
}
