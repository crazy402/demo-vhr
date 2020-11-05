package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.DepartmentMapper;
import com.crazy.demovhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/25 14:31
 * @Version 1.0
 **/
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartmentByParentId(-1);
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }
}
