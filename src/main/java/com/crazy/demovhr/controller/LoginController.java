package com.crazy.demovhr.controller;

import com.crazy.demovhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/19 12:39
 * @Version 1.0
 **/
@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登陆请登录！");
    }
}
