package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
@Rollback
public class DepartmentDaoJdbcImplTest {
    Integer NONEXISTENT_ID = 200;

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        assertNotNull(departmentDao.getDepartments());
    }

    @Test
    public void getDepartmentById() {
        assertNotNull(departmentDao.getDepartmentById(1));
    }

    @Test
    public void getNonexistentDepartmentById() {
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

    @Test
    public void addInvalidDepartment() {
        Department addedInvalidDepartment = new Department();
        assertThrows(DataIntegrityViolationException.class, () -> departmentDao.addDepartment(addedInvalidDepartment));
    }

    @Test
    public void addConflictDepartment() {
        Department department = new Department();
        department.setDepartmentName("HR");
        assertThrows(DataIntegrityViolationException.class, () -> departmentDao.addDepartment(department));
    }

    @Test
    public void updateDepartment() {
        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TEST");
        departmentDao.updateDepartment(department);
        assertEquals(department.getDepartmentName(), departmentDao.getDepartmentById(1).getDepartmentName());
    }

    @Test //exceptionassertEquals(
    public void updateInvalidDepartment() {
        Department department = departmentDao.getDepartmentById(1);
        department.setDepartmentName(null);
        assertThrows(DataIntegrityViolationException.class, () -> departmentDao.updateDepartment(department));
    }

    @Test //exception
    public void updateDepartmentConflict() {
        Department department = departmentDao.getDepartmentById(1);
        department.setDepartmentName("DEVELOPERS");
        assertThrows(DataIntegrityViolationException.class, () -> departmentDao.updateDepartment(department));
    }

    @Test
    public void deleteDepartment() {
        int recordsBeforeDelete = departmentDao.getDepartments().size();
        departmentDao.deleteDepartment(2);
        assertEquals(recordsBeforeDelete - 1, departmentDao.getDepartments().size());
    }
}