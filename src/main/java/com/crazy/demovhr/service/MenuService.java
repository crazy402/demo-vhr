package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.MenuMapper;
import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/19 21:25
 * @Version 1.0
 **/
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId() {
        return menuMapper.getMenuByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * @return
     * 获取所有的菜单选项 一对多一个菜单项又多个角色
     */
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
