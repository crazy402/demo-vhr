package com.crazy.demovhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author crazy402
 */
@SpringBootApplication
@MapperScan(basePackages = "com.crazy.demovhr.mapper")
public class DemoVhrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoVhrApplication.class, args);
    }

}
