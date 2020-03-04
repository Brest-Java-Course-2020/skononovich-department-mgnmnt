package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    /**
     * Get all departments.
     * @return departments list.
     */
    List<Department> getDepartments();

    /**
     * Get department by Id.
     * @param departmentId department Id.
     * @return department.
     */
    Optional<Department> getDepartmentById(Integer departmentId);

    /**
     * Create new department.
     * @param department department.
     * @return created department.
     */
    Department addDepartment(Department department);

    /**
     * Update department.
     * @param department department.
     * @return number of updated records.
     */
    int updateDepartment(Department department);

    /**
     * Delete department.
     * @param departmentId department id.
     * @return number of deleted records.
     */
    int deleteDepartment(Integer departmentId);
}
