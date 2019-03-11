package com.djdu.Manage.entity;

import com.djdu.common.base.BaseEmtity2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private boolean state;//管理员状态，0，可用，1，停用
}
