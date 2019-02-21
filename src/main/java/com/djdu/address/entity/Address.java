package com.djdu.address.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Address
 * @Description TODO 用于存储用户填写的收货地址
 * @Author DJDU
 * @Date 2019/2/20 15:29
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "address")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Address extends BaseEmtity2 {
    @Id
    @Column(name = "address_id")
    private String address_id;//收货地址id

    private String name;//收货人姓名

    private String phone;//手机号码

    private String province;//省

    private String city;//市

    private String addressDetails;//详细地址

    private boolean defaultAddress;//设置为默认收货地址

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;//父，用户


}
