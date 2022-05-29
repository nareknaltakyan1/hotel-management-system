package com.naltakyan.hotelmanagement.repository;

import com.naltakyan.hotelmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Long, Employee> {}
