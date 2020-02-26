package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbcImpl.class);

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    private static final String SQL_GET_DEPARTMENT_ID_NAME
            = "SELECT d.departmentId, d.departmentName FROM department d ORDER BY d.departmentName";
    private static final String SQL_GET_DEPARTMENT_BY_ID
            = "SELECT d.departmentId, d.departmentName FROM department d WHERE departmentId = :departmentId";
    private static final String SQL_UPDATE_DEPARTMENT
            = "UPDATE department SET departmentName = :departmentName WHERE departmentId = :departmentId";
    private static final String SQL_ADD_DEPARTMENT = "INSERT INTO department(departmentName) values (:departmentName)";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM department WHERE departmentId = :departmentId";




    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> getDepartments() {
        LOGGER.debug("Вызван метод getDepartments");
        return namedParameterJdbcTemplate.query(SQL_GET_DEPARTMENT_ID_NAME, new DepartmentRowMapper());
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("Вызван метод получения департамента по его ID = " + departmentId);
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("departmentId", departmentId);
        return namedParameterJdbcTemplate.queryForObject(
                SQL_GET_DEPARTMENT_BY_ID, parameters, new DepartmentRowMapper());
    }

    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("Вызван метод добаления департамента:" + department.toString());
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("departmentName", department.getDepartmentName());
        namedParameterJdbcTemplate.update(SQL_ADD_DEPARTMENT, parameters, keyHolder);
        Department addedDepartment = getDepartmentById(keyHolder.getKey().intValue());
        return addedDepartment;
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("Вызван метод обновления департамента с : id = " + department.getDepartmentId());
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departmentId", department.getDepartmentId());
        parameters.addValue("departmentName", department.getDepartmentName());
        namedParameterJdbcTemplate.update(SQL_UPDATE_DEPARTMENT, parameters);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        LOGGER.debug("Вызван метод удаления департамента : id = " + departmentId);
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("departmentId", departmentId);
        namedParameterJdbcTemplate.update(SQL_DELETE_DEPARTMENT, parameters);
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
