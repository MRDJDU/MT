package com.djdu.norms.service;

import com.djdu.brand.entity.Brand;
import com.djdu.category.entity.CategoryThird;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Base64ToImg;
import com.djdu.norms.entity.BaseNorms;
import com.djdu.norms.repository.BaseNormsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName BaseNormsService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 4:29
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class BaseNormsService {
    @Autowired
    BaseNormsRepository baseNormsRepository;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增基础属性
     * @Date 2019/4/12 5:17
     * @Param [baseNorms]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addBaseNorms(BaseNorms baseNorms){
        ResponseMessage responseMessage = new ResponseMessage<BaseNorms>();
        success = "基础属性新增成功";
        fail = "基础属性新增失败";
        try {
            if(baseNormsRepository.UnDeletedfindExistsName(baseNorms.getName(),baseNorms.getCategoryThird_id())>0){
                responseMessage.makeFail(fail,"基础属性已存在！");
                return responseMessage;
            }
            baseNorms.setBaseNorms_id(UUID.randomUUID().toString());
            baseNorms.setCreatTime(new Date());
            baseNorms.setUsable(Usable.UnDeleted);
            baseNormsRepository.save(baseNorms);
            responseMessage.makeSuccess(success);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }


    public ResponseMessage getBaseNormsPage(Specification specification, Pageable pageable){
        success = "分页查找成功！";
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<BaseNorms>>();
        try{
            responseMessage.makeSuccess(success,baseNormsRepository.findAll(specification,pageable));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }

    }


    public ResponseMessage deleteBaseNorms(BaseNorms baseNorms){
        ResponseMessage responseMessage = new ResponseMessage<BaseNorms>();
        success = "基础属性删除成功";
        fail = "基础属性删除失败";
        try {
            BaseNorms baseNorms1 = baseNormsRepository.findById(baseNorms.getBaseNorms_id()).get();
            baseNorms1.setUsable(Usable.Deleted);
            baseNormsRepository.save(baseNorms1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }
}
