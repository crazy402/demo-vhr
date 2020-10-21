package com.crazy.demovhr.controller.config;

import com.crazy.demovhr.model.Menu;
import com.crazy.demovhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SystemConfigController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/19 21:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenuByHrId() {
        return menuService.getMenuByHrId();
    }
}
