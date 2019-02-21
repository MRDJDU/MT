package com.djdu.brand.controller;

import com.djdu.brand.entity.Brand;
import com.djdu.brand.service.BrandService;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName BrandController
 * @Description TODO 中间层，接收前端请求数据，给service处理
 * @Author DJDU
 * @Date 2019/2/21 14:30
 * @Version 1.0
 **/

@RestController
@RequestMapping("/mt/brand")
public class BrandController {
    @Autowired
    BrandService brandService;//注入Service层

    /**
     * @Author DJDU
     * @Description TODO 新增品牌
     * @Date 2019/2/21 14:37
     * @Param [brand]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addBrand",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addBrand(@RequestBody Brand brand){
        ResponseMessage responseMessage = new ResponseMessage<Brand>();
        brand.setCreatTime(new Date());
        brand.setUsable(Usable.UnDeleted);
        try{
            brandService.save(brand);
            responseMessage.setStatuCode(true);
            responseMessage.setMessage("新增成功！");
            responseMessage.setData(brand);
        }catch (Exception e){
            responseMessage.setStatuCode(false);
            responseMessage.setMessage("新增失败！");
            responseMessage.setErrorMessage(e.getMessage());
        }
        return responseMessage;
    }


}
