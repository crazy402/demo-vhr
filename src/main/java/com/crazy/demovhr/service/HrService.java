package com.crazy.demovhr.service;

import com.crazy.demovhr.mapper.HrMapper;
import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/16 14:49
 * @Version 1.0
 **/
@Service
public class HrService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs() {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId());
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }
}
