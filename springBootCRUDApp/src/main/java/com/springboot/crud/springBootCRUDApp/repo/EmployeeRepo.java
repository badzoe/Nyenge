package com.springboot.crud.springBootCRUDApp.repo;

import com.springboot.crud.springBootCRUDApp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
