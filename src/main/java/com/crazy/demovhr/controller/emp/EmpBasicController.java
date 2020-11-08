package com.crazy.demovhr.controller.emp;

import com.crazy.demovhr.model.Employee;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.model.RespPageBean;
import com.crazy.demovhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @param page
     * @param size
     * @param keyword 搜索
     * @return
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        return employeeService.getEmployeeByPage(page,size,keyword);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        if (employeeService.addEmp(employee) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败！");
    }

}
