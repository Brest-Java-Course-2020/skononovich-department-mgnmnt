package com.epam.brest.courses.service;

import com.epam.brest.courses.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    /**
     * Find all departments.
     * @return departments list.
     */
    List<Department> findAll();

    /**
     * Find department by id.
     * @param departmentId department id.
     * @return department.
     */
    Optional<Department> findById(Integer departmentId);

    /**
     * Persist new department.
     * @param department department.
     * @return department id.
     */
    Integer create(Department department);

    /**
     * Update department.
     * @param department department.
     * @return number of updated records.
     */
    int update(Department department);

    /**
     * Delete department.
     * @param departmentId department id.
     * @return number of deleted records.
     */
    int delete(Integer departmentId);
}
