package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.DepartmentDao;
import com.epam.brest.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        LOGGER.debug("DepartmentService:findAll");
        return departmentDao.getDepartments();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("DepartmentService:findById = " + departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public Integer create(Department department) {
        LOGGER.debug("DepartmentService:create departmentName = " + department.getDepartmentId());
        return departmentDao.addDepartment(department);
    }

    @Override
    public int update(Department department) {
        LOGGER.debug("DepartmentService:Update departmentName = " + department.getDepartmentName());
        return departmentDao.updateDepartment(department);
    }

    @Override
    public int delete(Integer departmentId) {
        LOGGER.debug("DepartmentService:delete departmentId = " + departmentId);
        return departmentDao.deleteDepartment(departmentId);
    }
}
