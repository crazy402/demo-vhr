package com.crazy.demovhr.mapper;

import com.crazy.demovhr.model.MenuRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteById(Integer rid);

    Integer insertRecord(@Param("rid") Integer rid, @PathVariable("mids") Integer[] mids);
}