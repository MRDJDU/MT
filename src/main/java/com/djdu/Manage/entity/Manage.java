package com.djdu.Manage.entity;

import com.djdu.common.base.BaseEmtity2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Manage
 * @Description TODO 管理员表
 * @Author DJDU
 * @Date 2019/2/22 20:39
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "manage")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Manage extends BaseEmtity2 {
    @Id
    @Column(name = "manage_id")
    private String manage_id;//管理员ID

    private String name;//管理员登陆名

    private String password;//管理员密码

    private String img;//管理员头像

    private Integer state;//管理员状态，0，停用，1，可用

    private Integer grade;//管理员等级，0-超级管理员 1-普通管理员，超级管理员只能脚本添加

    @Transient
    private String token;//用于身份验证

    @Transient
    private String key;//脚本添加超级管理员时身份密码验证
}
