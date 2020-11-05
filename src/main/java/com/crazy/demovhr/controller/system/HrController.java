package com.crazy.demovhr.controller.system;

import com.crazy.demovhr.model.Hr;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName HrController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/3 10:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrs() {
        return hrService.getAllHrs();
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }
}
