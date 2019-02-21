package com.djdu.logistics.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName GetLogistics
 * @Description TODO 获取物流查询接口返回的信息
 * @Author DJDU
 * @Date 2019/2/20 20:12
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "getLogistics")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class GetLogistics {
    @Id
    @Column(name = "getLogistics_id")
    private String getLogisticsDto_id;//获取物流查询接口返回的信息id

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date time;//时间

    private String context;//信息

    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name = "logistics_id")
    private Logistics logistics;//父，物流表
}
