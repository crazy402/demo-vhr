package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.RoleMapper;
import com.crazy.demovhr.model.Menu;
import com.crazy.demovhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/23 11:17
 * @Version 1.0
 **/
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

}
