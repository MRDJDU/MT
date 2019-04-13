package com.djdu.norms.controller;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.category.entity.CategoryThird;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import com.djdu.norms.dto.BaseNormsDto;
import com.djdu.norms.entity.BaseNorms;
import com.djdu.norms.service.BaseNormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName BaseNormsController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 4:30
 * @Version 1.0
 **/

@RestController
@RequestMapping("/mt/BaseNorms")
public class BaseNormsController {
    @Autowired
    BaseNormsService baseNormsService;
    private String success = "";
    private String fail = "";

    @PostMapping(value="/addBaseNorms",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addCategorySecond(@RequestBody BaseNorms baseNorms){
        return baseNormsService.addBaseNorms(baseNorms);
    }

    @PostMapping(value="/getBaseNormsPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getBrandPage(@RequestBody Map<String, Object> models){
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Brand>>();
        try {
            BaseNormsDto baseNormsDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("baseNormsDto"), BaseNormsDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return baseNormsService.getBaseNormsPage(BaseNormsDto.getWhereClause(baseNormsDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    @PostMapping(value="/deleteBaseNorms",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteBaseNorms(@RequestBody BaseNorms baseNorms){
        return baseNormsService.deleteBaseNorms(baseNorms);
    }
}
