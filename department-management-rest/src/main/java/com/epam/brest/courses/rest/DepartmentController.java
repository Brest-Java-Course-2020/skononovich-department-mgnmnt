package com.epam.brest.courses.rest;

import com.epam.brest.courses.model.Department;
import com.epam.brest.courses.model.DepartmentDto;
import com.epam.brest.courses.rest.exception.DepartmentNotFoundException;
import com.epam.brest.courses.service.DepartmentDtoService;
import com.epam.brest.courses.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController(value = "/api")
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;
    private final DepartmentDtoService departmentDtoService;

    public DepartmentController(DepartmentService departmentService, DepartmentDtoService departmentDtoService){
        this.departmentService = departmentService;
        this.departmentDtoService = departmentDtoService;
    }

    @GetMapping(value = "/departments")
    public final Collection<DepartmentDto> departments(){
        LOGGER.debug("Rest GET:/departments");
        return departmentDtoService.findAllWithAvgSalary();
    }

    @GetMapping(value = "/departments/{id}")
    public final Department findById(@PathVariable Integer id){
        LOGGER.debug("Rest GET:/departments/" + id);
        return departmentService.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @PostMapping(path = "/departments", consumes = "application/json", produces = "application/json")
    public Integer add(@RequestBody String departmentName){
        LOGGER.debug("Rest POST:/departments");
        return departmentService.create(new Department(departmentName));
    }
}
