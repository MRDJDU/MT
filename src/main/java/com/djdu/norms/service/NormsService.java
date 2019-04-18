package com.djdu.norms.service;

import com.djdu.norms.repository.NormsRepository;
import com.djdu.sku.repository.SKUValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName NormsService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:55
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class NormsService {
    @Autowired
    NormsRepository normsRepository;
    private String success = "";
    private String fail = "";
}
