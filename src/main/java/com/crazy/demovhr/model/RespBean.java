package com.crazy.demovhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RespBean
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/16 15:25
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespBean ok(String msg) {
        return new RespBean(200,msg,null);
    }
    public static RespBean ok(String msg,Object obj) {
        return new RespBean(200,msg,obj);
    }
    public static RespBean error(String msg) {
        return new RespBean(500,msg,null);
    }
    public static RespBean error(String msg,Object obj) {
        return new RespBean(500,msg,obj);
    }


}
