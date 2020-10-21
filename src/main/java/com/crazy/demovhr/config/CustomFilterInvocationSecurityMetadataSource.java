package com.crazy.demovhr.config;

import com.crazy.demovhr.model.Menu;
import com.crazy.demovhr.model.Role;
import com.crazy.demovhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomFilterInvocationSecurityMetadataSource
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/20 19:08
 * @Version 1.0
 * 这个类的作用 主要根据用户传来的请求地址 分析出需要的角色
 **/
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //从FilterInvocation中获取当前请求的地址，拿到地址后，去数据库里面匹配菜单项
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        List<Menu> menus = menuService.getAllMenusWithRole();
        //比较request跟menu中的url是否一致 借用antPathMatcher工具进行
        for (Menu menu : menus) {
            //String pattern:menus里面的规则
            if (antPathMatcher.match(menu.getUrl(),requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        //如果没人匹配上  登录上可以访问 ROLE_LOGIN只是一个标记
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
