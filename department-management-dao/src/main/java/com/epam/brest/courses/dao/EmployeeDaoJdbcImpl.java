package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoJdbcImpl implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoJdbcImpl.class);

    private KeyHolder keyHolder = new GeneratedKeyHolder();



    @Value("${employee.sqlGetEmployees}")
    String sqlGetEmployees;

    @Value("${employee.sqlGetEmployeeById}")
    String sqlGetEmployeeById;

    @Value("${employee.sqlAddEmployee}")
    String sqlAddEmployee;

    @Value("${employee.sqlUpdateEmployee}")
    String sqlUpdateEmployee;

    @Value("${employee.sqlDeleteEmployee}")
    String sqlDeleteEmployee;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        LOGGER.debug("Вызван метод getDepartments");
        return namedParameterJdbcTemplate.query(sqlGetEmployees, new EmployeeRowMapper());
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        LOGGER.debug("Вызван метод getEmployeeById");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employeeId", employeeId);
        return namedParameterJdbcTemplate.queryForObject(sqlGetEmployeeById, parameters, new EmployeeRowMapper());
    }

    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("Вызван метод добаления сотрудника:" + employee.toString());
        namedParameterJdbcTemplate.update(sqlAddEmployee, getParameter(employee), keyHolder);
        Employee addedEmployee = getEmployeeById(keyHolder.getKey().intValue());
        return addedEmployee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        LOGGER.debug("Вызван метод обновления сотрудника с : id = " + employee.getEmployeeId());
        namedParameterJdbcTemplate.update(sqlUpdateEmployee, getParameter(employee));
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        LOGGER.debug("Вызван метод удаления департамента : id = " + employeeId);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("employeeId", employeeId);
        namedParameterJdbcTemplate.update(sqlDeleteEmployee, parameters);
    }

    private MapSqlParameterSource getParameter(Employee employee){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("employeeId", employee.getEmployeeId());
        parameters.addValue("departmentId", employee.getDepartmentId());
        parameters.addValue("firstName", employee.getFirstName());
        parameters.addValue("lastName", employee.getLastName());
        parameters.addValue("salary", employee.getSalary());
        return parameters;
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
