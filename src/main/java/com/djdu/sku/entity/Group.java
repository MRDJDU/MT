package com.djdu.sku.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Group
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:59
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "group")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Group {
    @Id
    @Column(name = "group_id")
    private String group_id;

    private String SKU_id;
}
