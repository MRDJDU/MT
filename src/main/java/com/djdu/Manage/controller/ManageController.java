package com.djdu.Manage.controller;

import com.djdu.Manage.entity.Manage;
import com.djdu.Manage.service.ManageService;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value="/login", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage login(@RequestBody Manage manage){
        return manageService.login(manage);
    }
}
