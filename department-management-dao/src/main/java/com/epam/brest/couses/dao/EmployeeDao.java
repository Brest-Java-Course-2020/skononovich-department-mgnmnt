package com.epam.brest.couses.dao;

import com.epam.brest.courses.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployees();

    Employee getEmployeeById(Integer employeeId);

    Employee addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer employeeId);
}
