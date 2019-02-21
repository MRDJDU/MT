package com.djdu.common.base;

import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @ClassName BaseEmtity2
 * @Description TODO 基础公共类，实体共有的属性
 * @Author DJDU
 * @Date 2019/2/20 16:32
 * @Version 1.0
 **/
@Data
@MappedSuperclass
public class BaseEmtity2 {

    private Usable usable;//是否被删除

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date creatTime;//创建时间

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;//更新时间
}
