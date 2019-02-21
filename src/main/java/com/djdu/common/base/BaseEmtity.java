package com.djdu.common.base;

import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseEmtity
 * @Description TODO 基础公共类，实体共有的属性
 * @Author DJDU
 * @Date 2019/2/20 14:15
 * @Version 1.0
 **/
@Data
@MappedSuperclass
public class BaseEmtity extends BaseEmtity2{
    private ShowOut showOut;//是否展示在用户端
}
