package com.crazy.demovhr.controller.emp;

import com.crazy.demovhr.model.RespPageBean;
import com.crazy.demovhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmpBasicController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/5 18:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPage(page,size);
    }

}
