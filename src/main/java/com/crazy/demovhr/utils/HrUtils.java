package com.crazy.demovhr.utils;

import com.crazy.demovhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName HrUtils
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/3 10:46
 * @Version 1.0
 * 获取Security中用户对象
 **/
public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
