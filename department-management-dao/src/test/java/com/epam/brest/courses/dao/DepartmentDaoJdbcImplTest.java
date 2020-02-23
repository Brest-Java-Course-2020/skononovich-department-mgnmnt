package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class DepartmentDaoJdbcImplTest {
    Integer NONEXISTENT_ID = 200;

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        assertNotNull(departmentDao.getDepartments());
        assertEquals(3, departmentDao.getDepartments().size());
    }

    @Test
    public void getDepartmentById() {
        assertNotNull(departmentDao.getDepartmentById(1));
    }

    @Test
    public void getNonexistentDepartmentById(){
        assertThrows(EmptyResultDataAccessException.class, () -> departmentDao.getDepartmentById(NONEXISTENT_ID));
    }

    @Test
    public void addDepartment() {
        Department addedDepartment = new Department();
        addedDepartment.setDepartmentName("SECURITY");
        Department returnedDepartment = departmentDao.addDepartment(addedDepartment);

        assertNotNull(returnedDepartment);
        assertNotNull(returnedDepartment.getDepartmentId());
        assertEquals("SECURITY", returnedDepartment.getDepartmentName());
    }

    @Test //exception
    public void addInvalidDepartment(){
        Department addedInvalidDepartment = new Department();
        departmentDao.addDepartment(addedInvalidDepartment);
    }

    @Test //exception
    public void addConflictDepartment(){
        Department department = new Department();
        department.setDepartmentName("SEO");
        departmentDao.addDepartment(department);
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("ACCOUNTING");
        departmentDao.updateDepartment(department);
        assertEquals(department, departmentDao.getDepartmentById(1));
    }

    @Test //exception
    public void updateInvalidDepartment(){
        Department department = departmentDao.getDepartmentById(1);
        department.setDepartmentName(null);
        departmentDao.updateDepartment(department);
    }

    @Test //exception
    public void updateDepartmentConflict(){
        Department department = departmentDao.getDepartmentById(1);
        department.setDepartmentName("DEVELOPERS");
        departmentDao.updateDepartment(department);
    }

    @Test
    public void deleteDepartment() {
        assertEquals(1, departmentDao.deleteDepartment(2));
        assertEquals(0, departmentDao.deleteDepartment(NONEXISTENT_ID));
    }
}