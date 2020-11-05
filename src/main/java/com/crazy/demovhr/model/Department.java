package com.crazy.demovhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazy402
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;

    private String name;

    private Integer parentId;

    private String depPath;

    private Boolean enabled;

    private Boolean isParent;

    //先对children进行声明 防止出现为null的情况
    private List<Department> children = new ArrayList<>();

    private Integer result;

}