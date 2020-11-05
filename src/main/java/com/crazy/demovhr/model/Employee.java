package com.crazy.demovhr.model;

import lombok.Data;

import java.util.Date;

/**
 * @author crazy402
 */
@Data
public class Employee {
    private Integer id;

    private String name;

    private String gender;

    private Date birthday;

    private String idCard;

    private String wedlock;

    //名族
    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    //最高学历
    private String tiptopDegree;

    private String specialty;

    private String school;

    //开始时间
    private Date beginDate;

    //在职状态
    private String workState;

    private String workId;

    private Double contractTerm;

    private Date conversionTime;

    private Date notWorkDate;

    private Date beginContract;

    private Date endContract;

    private Integer workAge;

    //一对一关系的对象
    private Nation nation;

    private Politicsstatus politicsstatus;

    private Department department;

    private JobLevel jobLevel;

    private Position position;

}