package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.EmployeeDao;
import com.epam.brest.courses.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        LOGGER.debug("EmployeeService:findAll");
        return employeeDao.getEmployees();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        LOGGER.debug("EmployeeService:findById employeeId = " + employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Integer create(Employee employee) {
        LOGGER.debug("EmployeeService:create employeeName = " + employee.getFirstName() + " " + employee.getLastName());
        return employeeDao.addEmployee(employee);
    }

    @Override
    public int update(Employee employee) {
        LOGGER.debug("EmployeeService:update employeeName = " + employee.getFirstName() + " " + employee.getLastName());
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int delete(Integer employeeId) {
        LOGGER.debug("EmployeeService:delete employeeId = " + employeeId);
        return employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        LOGGER.debug("EmployeeService:findByDepartmentId departmentId = " + departmentId);
        return employeeDao.getEmployees();
    }
}
