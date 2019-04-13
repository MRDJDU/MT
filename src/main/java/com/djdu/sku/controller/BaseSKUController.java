package com.djdu.sku.controller;

import com.djdu.brand.entity.Brand;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import com.djdu.norms.dto.BaseNormsDto;
import com.djdu.norms.entity.BaseNorms;
import com.djdu.norms.service.BaseNormsService;
import com.djdu.sku.dto.BaseSKUDto;
import com.djdu.sku.entity.BaseSKU;
import com.djdu.sku.service.BaseSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName BaseSKUController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 21:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/BaseSKU")
public class BaseSKUController {
    @Autowired
    BaseSKUService baseSKUService;
    private String success = "";
    private String fail = "";

    @PostMapping(value="/addBaseSKU",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addBaseSKU(@RequestBody BaseSKU baseSKU){
        return baseSKUService.addBaseSKU(baseSKU);
    }

    @PostMapping(value="/getBaseSKUPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getBaseSKUPage(@RequestBody Map<String, Object> models){
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Brand>>();
        try {
            BaseSKUDto baseSKUDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("baseSKUDto"), BaseSKUDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return baseSKUService.getBaseSKUPage(BaseSKUDto.getWhereClause(baseSKUDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    @PostMapping(value="/deleteBaseSKU",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteBaseSKU(@RequestBody BaseSKU baseSKU){
        return baseSKUService.deleteBaseSKU(baseSKU);
    }
}
