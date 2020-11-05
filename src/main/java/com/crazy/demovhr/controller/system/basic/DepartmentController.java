package com.crazy.demovhr.controller.system.basic;

import com.crazy.demovhr.model.Department;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DepartmentController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/25 14:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        departmentService.addDep(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功",dep);
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }
}
