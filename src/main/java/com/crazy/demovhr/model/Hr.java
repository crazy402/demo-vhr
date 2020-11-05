package com.crazy.demovhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hr implements UserDetails {
    private Integer id;

    private String name;

    private String phone;

    private String telephone;

    private String address;

    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;

    private String username;

    private String password;

    private String userface;

    private String remark;

    private List<Role> roles;


    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return 账户是否被过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return 账户是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return 是否可用
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return 返回用户所有角色
     * 忽略掉传给前端的信息
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

}