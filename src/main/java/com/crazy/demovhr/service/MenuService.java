package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.MenuMapper;
import com.crazy.demovhr.mapper.MenuRoleMapper;
import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.model.Menu;
import com.crazy.demovhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    MenuRoleMapper menuRoleMapper;

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

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        //先删除 再添加
        menuRoleMapper.deleteById(rid);
        Integer result = menuRoleMapper.insertRecord(rid,mids);

        return result == mids.length;
    }

}
