package com.epam.brest.courses.dao;
import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
@Rollback
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
        Employee employee = new Employee();
        employee.setDepartmentId(10);
        employee.setFirstName("fname");
        employee.setLastName("lname");
        employee.setSalary((double) 40);
        Integer empleId = employeeDao.addEmployee(employee);

        Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(empleId);

        assertTrue(optionalEmployee.isPresent());
        assertEquals(optionalEmployee.get().getSalary(), employee.getSalary());
        assertEquals(optionalEmployee.get().getFirstName(), employee.getFirstName());
        assertEquals(optionalEmployee.get().getLastName(), employee.getLastName());
        assertEquals(optionalEmployee.get().getDepartmentId(), employee.getDepartmentId());
    }

    @Test
    public void addEmployee() {
        Employee addedEmployee = new Employee();
        addedEmployee.setDepartmentId(1);
        addedEmployee.setFirstName("FirstName");
        addedEmployee.setLastName("LastName");

        Integer empleId = employeeDao.addEmployee(addedEmployee);

        assertNotNull(empleId);
    }

    @Test
    public void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(3);
        employee.setDepartmentId(1);
        employee.setFirstName("FName");
        employee.setLastName("LName");
        employee.setSalary(0d);
        employeeDao.updateEmployee(employee);
        Employee addedEmployee = employeeDao.getEmployeeById(3).get();
        assertEquals(employee.getEmployeeId(), addedEmployee.getEmployeeId());
        assertEquals(employee.getDepartmentId(), addedEmployee.getDepartmentId());
        assertEquals(employee.getFirstName(), addedEmployee.getFirstName());
        assertEquals(employee.getLastName(), addedEmployee.getLastName());
        assertEquals(employee.getSalary(), addedEmployee.getSalary());
    }

    @Test
    public void deleteEmployee() {
        employeeDao.deleteEmployee(4);
        assertThrows(EmptyResultDataAccessException.class, () -> employeeDao.getEmployeeById(4));
    }
}