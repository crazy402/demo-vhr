package com.crazy.demovhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author crazy402
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Integer parentId;

    private Boolean enabled;

    private Meta meta;

    private List<Menu> children;
    //菜单项需要那些角色访问
    private List<Role> roles;


}