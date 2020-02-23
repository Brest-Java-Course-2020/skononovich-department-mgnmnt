package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoJdbcImpl implements EmployeeDao {
    @Override
    public List<Employee> getEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return null;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(Integer employeeId) {

    }

    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setDepartmentId(resultSet.getInt("DEPARTMENTID"));
            employee.setEmployeeId(resultSet.getInt("EMPLOYEEID"));
            employee.setFirstName(resultSet.getString("FIRSTNAME"));
            employee.setLastName(resultSet.getString("LASTNAME"));
            employee.setSalary(resultSet.getDouble("SALARY"));
            return employee;
        }
    }
}
