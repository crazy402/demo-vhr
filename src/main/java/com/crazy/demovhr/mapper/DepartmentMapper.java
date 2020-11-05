package com.crazy.demovhr.mapper;

import com.crazy.demovhr.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartmentByParentId(Integer pid);

    void addDep(Department dep);

    void deleteDepById(Department dep);
}