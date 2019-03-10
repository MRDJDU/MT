package com.djdu.brand.controller;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.brand.service.BrandService;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import com.djdu.common.Tool.MTLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BrandController
 * @Description TODO 中间层，接收前端请求数据，给service处理，品牌id，品牌名，是否删除，创建时间，修改时间
 * @Author DJDU
 * @Date 2019/2/21 14:30
 * @Version 1.0
 **/

@RestController
@RequestMapping("/mt/brand")
public class BrandController {
    @Autowired
    BrandService brandService;//注入Service层
    private String success = "";
    private String fail = "";
    /**
     * @Author DJDU
     * @Description TODO 新增品牌
     * @Date 2019/2/21 14:37
     * @Param [brand]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addBrand",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addBrand(@RequestBody Brand brand){
        return brandService.addBrand(brand);
    }

    /**
     * @Author DJDU
     * @Description TODO 修改单个品牌实体的全部信息
     * @Date 2019/2/25 8:43
     * @Param [brandDto]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PutMapping(value="/editBrand",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage editBrand(@RequestBody BrandDto brandDto){
        return brandService.editBrand(brandDto);
    }

    /**
     * @Author DJDU
     * @Description TODO 批量修改品牌实体是否展示在客户端
     * @Date 2019/2/26 9:04
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PutMapping(value="/showOutAll",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage showOutAll(@RequestBody Map<String, Object> models){
        ArrayList<String> brand_ids  = (ArrayList<String>) models.get("brand_ids");
        ShowOut showOut  = ShowOut.valueOf( (String) models.get("showOut"));
        return brandService.showOutAll(brand_ids,showOut);
    }

    /**
     * @Author DJDU
     * @Description TODO 处理前端获取品牌的请求,前端请求有json格式的多个对象参数时封装在map再转对象，分页条件对象和查询条件对象（分类名字和是否显示）
     * @Date 2019/2/26 19:54 
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @GetMapping(value="/getBrandPage",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getBrandPage(@RequestBody Map<String, Object> models){
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Brand>>();
        try {
            BrandDto brandDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("brandDto"), BrandDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return brandService.getBrandPage(BrandDto.getWhereClause(brandDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


}
