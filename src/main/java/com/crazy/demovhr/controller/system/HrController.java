package com.crazy.demovhr.controller.system;

import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.model.Role;
import com.crazy.demovhr.service.HrService;
import com.crazy.demovhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName HrController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/3 10:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHrs() {
        return hrService.getAllHrs();
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid,rids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }
}
