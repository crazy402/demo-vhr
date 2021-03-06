package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.EmployeeMapper;
import com.crazy.demovhr.model.Employee;
import com.crazy.demovhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/5 18:39
 * @Version 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }

        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, keyword);
        Long total = employeeMapper.getTotal(keyword);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }
}
