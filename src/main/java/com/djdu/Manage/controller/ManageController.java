package com.djdu.Manage.controller;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
import com.djdu.Manage.service.ManageService;
import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ManageController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/3/11 10:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/manage")
public class ManageController {
    @Autowired
    ManageService manageService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增管理员
     * @Date 2019/3/11 10:19
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addManage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addManage(@RequestBody Manage manage){
        return manageService.addManage(manage);
    }

    /**
     * @Author DJDU
     * @Description TODO 登陆
     * @Date 2019/3/11 15:23
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/login", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage login(@RequestBody Manage manage){
        return manageService.login(manage);
    }

    /**
     * @Author DJDU
     * @Description TODO 分页查找管理员列表
     * @Date 2019/3/13 21:59
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/findAllManage", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getPage(@RequestBody Map<String, Object> models){
        fail = "分页查找管理员列表失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Manage>>();
        try {
            ManageDto manageDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("manageDto"), ManageDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return manageService.getBrandPage(ManageDto.getWhereClause(manageDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
