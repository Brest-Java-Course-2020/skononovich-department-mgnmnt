package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbcImpl.class);

    private static final String SQL_GET_DEPARTMENT_ID_NAME
            = "SELECT d.departmentId, d.departmentName FROM department d ORDER BY d.departmentName";
    private static final String SQL_GET_DEPARTMENT_BY_ID
            = "SELECT d.departmentId, d.departmentName FROM department d WHERE departmentId=:departmentId";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.info("Вызван метод getDepartments");
        List<Department> departments = namedParameterJdbcTemplate
                .query(SQL_GET_DEPARTMENT_ID_NAME, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
//        Department department = new namedParameterJdbcTemplate.queryForObject(
//                SQL_GET_DEPARTMENT_BY_ID, new MapSqlParameterSource("departmentId", departmentId), Department.class);
//        return department;
    return null;
    }

    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("DEPARTMENTID"));
            department.setDepartmentName(resultSet.getString("DEPARTMENTNAME"));
            return department;
        }
    }
}
