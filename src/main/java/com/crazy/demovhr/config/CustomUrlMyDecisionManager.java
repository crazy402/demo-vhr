package com.crazy.demovhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName CustomUrlMyDecisionManager
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/21 10:14
 * @Version 1.0
 **/
@Component
public class CustomUrlMyDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication 当前登录的用户
     * @param o 请求的对象
     * @param collection 是CustomFilterInvocationSecurityMetadataSource类中的getAttributes方法的返回值
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //遍历需要的角色
        for (ConfigAttribute configAttribute : collection) {
            //需要的角色
            String needRole = configAttribute.getAttribute();
            //如果需要的角色是ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)) {
                //如果当前用户是匿名用户的实例话 就是没登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //没登陆就抛出异常
                    throw new AccessDeniedException("尚未登陆，请登录！");
                }else {
                    return;
                }
            }
            //获取当前登录的用户角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority authority : authorities) {

                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
