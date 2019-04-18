package com.djdu.goods.controller;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import com.djdu.goods.dto.AddgoodsDto;
import com.djdu.goods.dto.GoodsDto;
import com.djdu.goods.dto.NormsDto;
import com.djdu.goods.dto.SKUDto;
import com.djdu.goods.entity.Goods;
import com.djdu.goods.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 16:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 添加商品
     * @Date 2019/4/18 14:42
     * @Param [addgoodsDto]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addGoods",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addGoods(@RequestBody AddgoodsDto addgoodsDto){
        return goodsService.addGoods(addgoodsDto);
    }

    /**
     * @Author DJDU
     * @Description TODO 分页查找商品
     * @Date 2019/4/18 14:43
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/findAllGoods", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getPage(@RequestBody Map<String, Object> models){
        fail = "分页查找商品列表失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Manage>>();
        try {
            AddgoodsDto addgoodsDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("addgoodsDto"), AddgoodsDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            GoodsDto goodsDto = new GoodsDto();
            if(StringUtils.isNotBlank(addgoodsDto.getGoodsname())){
                goodsDto.setName(addgoodsDto.getGoodsname());
            }
            if(addgoodsDto.getSelectedaddOptions()!=null && addgoodsDto.getSelectedaddOptions().size()>0){
                goodsDto.setCategory_id(addgoodsDto.getSelectedaddOptions().get(0) +"/"+addgoodsDto.getSelectedaddOptions().get(1)+"/"+addgoodsDto.getSelectedaddOptions().get(2));
            }
            return goodsService.getPage(GoodsDto.getWhereClause(goodsDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }



    @PostMapping(value="/deteleGoods",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deteleGoods(@RequestBody Map<String, Object> models){
        ArrayList<String> ids  = (ArrayList<String>) models.get("ids");
        return goodsService.deteleGoods(ids);
    }


}
