package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentDaoJdbcImplMockTest {

    @InjectMocks
    private DepartmentDaoJdbcImpl departmentDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @AfterEach
    void after(){
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        assertNotNull(departments);

        Mockito.verify(namedParameterJdbcTemplate).query(anyString(), any(RowMapper.class));
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