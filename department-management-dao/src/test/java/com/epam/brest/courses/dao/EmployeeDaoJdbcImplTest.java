package com.epam.brest.courses.dao;
import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
public class EmployeeDaoJdbcImplTest {
    Integer NONEXISTENT_ID = 200;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void getEmployees() {
        assertNotNull(employeeDao.getEmployees());
        assertEquals(4, employeeDao.getEmployees().size());
    }

    @Test
    public void getEmployeeById() {
        assertNotNull(employeeDao.getEmployeeById(1));
        assertEquals((Integer)1, employeeDao.getEmployeeById(1).getDepartmentId());
    }

    @Test
    public void addEmployee() {
        Employee addedEmployee = new Employee();
        addedEmployee.setDepartmentId(1);
        addedEmployee.setFirstName("FirstName");
        addedEmployee.setLastName("LastName");
        Employee returnedEmployee = employeeDao.addEmployee(addedEmployee);

        assertNotNull(returnedEmployee);
        assertNotNull(returnedEmployee.getDepartmentId());
        assertEquals((Integer)1, returnedEmployee.getDepartmentId());
        assertEquals("FirstName", returnedEmployee.getFirstName());
        assertEquals("LastName", returnedEmployee.getLastName());
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(3);
        employee.setDepartmentId(1);
        employee.setFirstName("FName");
        employee.setLastName("LName");
        employeeDao.updateEmployee(employee);
        assertEquals(employee, employeeDao.getEmployeeById(3));
    }

    @Test
    public void deleteEmployee() {
        employeeDao.deleteEmployee(4);
        assertThrows(EmptyResultDataAccessException.class, () -> employeeDao.getEmployeeById(4));
    }
}