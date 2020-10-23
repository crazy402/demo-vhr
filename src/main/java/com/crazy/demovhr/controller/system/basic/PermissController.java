package com.crazy.demovhr.controller.system.basic;

import com.crazy.demovhr.model.Menu;
import com.crazy.demovhr.model.Role;
import com.crazy.demovhr.service.MenuService;
import com.crazy.demovhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PermissController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/23 11:15
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }
}
