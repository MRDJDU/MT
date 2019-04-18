package com.djdu.imageResource.service;

import com.djdu.goods.repository.GoodsRepository;
import com.djdu.imageResource.repository.ImageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ImageResourceService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:54
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class ImageResourceService {
    @Autowired
    ImageResourceRepository imageResourceRepository;
    private String success = "";
    private String fail = "";
}
