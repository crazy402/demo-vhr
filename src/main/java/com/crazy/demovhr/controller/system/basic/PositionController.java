package com.crazy.demovhr.controller.system.basic;

import com.crazy.demovhr.model.Position;
import com.crazy.demovhr.model.RespBean;
import com.crazy.demovhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PositionController
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/21 20:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable Integer id) {
        if (positionService.deletePositionById(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败！");
    }
}
