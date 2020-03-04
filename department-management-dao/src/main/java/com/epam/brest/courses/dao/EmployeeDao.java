package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    /**
     * Get all employees.
     *
     * @return list employees.
     */
    List<Employee> getEmployees();

    /**
     * Get employee by id.
     *
     * @param employeeId employee id.
     * @return employee.
     */
    Optional<Employee> getEmployeeById(Integer employeeId);

    /**
     * Add new employee.
     *
     * @param employee employee.
     * @return id added employee.
     */
    int addEmployee(Employee employee);

    /**
     * Update employee.
     * @param employee employee.
     * @return number of updated records.
     */
    int updateEmployee(Employee employee);


    /**
     * Delete employee.
     * @param employeeId employee id.
     * @return number of deleted record.
     */
    int deleteEmployee(Integer employeeId);

}
