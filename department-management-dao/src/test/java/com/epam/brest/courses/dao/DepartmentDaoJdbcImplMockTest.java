package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentDaoJdbcImplMockTest {

    @InjectMocks
    private DepartmentDaoJdbcImpl departmentDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Department>> mapper; //перехват RowMap`ера

    @AfterEach
    void after(){
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void getDepartments() throws SQLException {
        Department department = new Department();
        ResultSet rs = mock(ResultSet.class);
        int id=5;
        String name = "name";

        when(namedParameterJdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(Collections.singletonList(department));
        when(rs.getInt(anyString())).thenReturn(5);
        when(rs.getString(anyString())).thenReturn("name");


        List<Department> departments = departmentDao.getDepartments();
        assertNotNull(departments);
        assertEquals(1, departments.size());
        Department dep = departments.get(0);
        assertSame(dep, department);

        Mockito.verify(namedParameterJdbcTemplate).query(
                eq("SELECT d.departmentId, d.departmentName FROM department d ORDER BY d.departmentName"), mapper.capture());

        RowMapper<Department> rowMapper = mapper.getValue();
        assertNotNull(rowMapper);
        Department departmentRowMap = rowMapper.mapRow(rs, 0);
        assertNotNull(departmentRowMap);
        assertEquals(id, departmentRowMap.getDepartmentId().intValue());
        assertEquals(name, departmentRowMap.getDepartmentName());
    }

    @Test
    public void getDepartmentById() {
    }

    @Test
    public void addDepartment() {
    }

    @Test
    public void addInvalidDepartment(){
    }

    @Test
    public void updateDepartment() {
    }

    @Test
    public void deleteDepartment() {
    }
}