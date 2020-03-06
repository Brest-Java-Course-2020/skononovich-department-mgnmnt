package com.epam.brest.courses.service;

import com.epam.brest.courses.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    /**
     * Find all employees.
     * @return employees list.
     */
    List<Employee> findAll();

    /**
     * Find employee by id.
     * @param employeeId employee id.
     * @return employee.
     */
    Optional<Employee> findById(Integer employeeId);

    /**
     * Persist new employee.
     * @param employee employee.
     * @return employee id.
     */
    Integer create(Employee employee);

    /**
     * Update employee.
     * @param employee employee.
     * @return number of updated records.
     */
    int update(Employee employee);

    /**
     * Delete employee.
     * @param employeeId employee id.
     * @return number of deleted records.
     */
    int delete(Integer employeeId);

    /**
     * Get all employees with specified department id.
     *
     * @param departmentId department id
     * @return list of employees by department id
     */
    List<Employee> findByDepartmentId(Integer departmentId);
}
