package com.djdu.sku.service;

import com.djdu.goods.repository.GoodsRepository;
import com.djdu.sku.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName GroupService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:04
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    private String success = "";
    private String fail = "";
}
