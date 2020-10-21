package com.crazy.demovhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Meta
 * @Description //TODO
 * @Author crazy402
 * @Date 2020/10/19 21:17
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Boolean keepAlive;

    private Boolean requireAuth;
}
