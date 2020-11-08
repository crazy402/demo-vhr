package com.crazy.demovhr.model;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RespPageBean
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/11/5 18:34
 * @Version 1.0
 **/
@Data
public class RespPageBean {
    private Long total;
    private List<?> data;
}
