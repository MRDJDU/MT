package com.djdu.sku.service;

import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.norms.entity.BaseNorms;
import com.djdu.sku.entity.BaseSKU;
import com.djdu.sku.repository.BaseSKURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName BaseSKUService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 21:33
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class BaseSKUService {
    @Autowired
    BaseSKURepository baseSKURepository;
    private String success = "";
    private String fail = "";


    public ResponseMessage addBaseSKU(BaseSKU baseSKU){
        ResponseMessage responseMessage = new ResponseMessage<BaseSKU>();
        success = "库存计量属性新增成功";
        fail = "库存计量属性新增失败";
        try {
            if(baseSKURepository.UnDeletedfindExistsName(baseSKU.getName(),baseSKU.getCategoryThird_id())>0){
                responseMessage.makeFail(fail,"库存计量属性已存在！");
                return responseMessage;
            }
            baseSKU.setBaseSKU_id(UUID.randomUUID().toString());
            baseSKU.setCreatTime(new Date());
            baseSKU.setUsable(Usable.UnDeleted);
            baseSKURepository.save(baseSKU);
            responseMessage.makeSuccess(success);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }


    public ResponseMessage getBaseSKUPage(Specification specification, Pageable pageable){
        success = "分页查找成功！";
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<BaseSKU>>();
        try{
            responseMessage.makeSuccess(success,baseSKURepository.findAll(specification,pageable));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }

    }


    public ResponseMessage deleteBaseSKU(BaseSKU baseSKU){
        ResponseMessage responseMessage = new ResponseMessage<BaseNorms>();
        success = "库存计量属性删除成功";
        fail = "库存计量属性删除失败";
        try {
            BaseSKU baseSKU1 = baseSKURepository.findById(baseSKU.getBaseSKU_id()).get();
            baseSKU1.setUsable(Usable.Deleted);
            baseSKURepository.save(baseSKU1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }


}
