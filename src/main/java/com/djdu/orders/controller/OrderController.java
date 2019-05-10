package com.djdu.orders.controller;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import com.djdu.goods.dto.AddgoodsDto;
import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.orders.dto.SearchOrdersDto;
import com.djdu.orders.dto.addOderDto;
import com.djdu.orders.dto.findOdersDto;
import com.djdu.orders.entity.Orders;
import com.djdu.orders.entity.addr;
import com.djdu.orders.service.ordersService;
import com.djdu.shoppingCart.entity.ShoppingCart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 14:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/order")
public class OrderController {
    @Autowired
    ordersService ordersService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 添加购物车
     * @Date 2019/5/9 10:52
     * @Param [shoppingCart]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addorder",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addorder(@RequestBody addOderDto add){
            return ordersService.addorder(add);
    }



    @PostMapping(value="/changeorder",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage changeorder(@RequestBody Orders orders){
        return ordersService.changeorder(orders);
    }

    @PostMapping(value="/findnum",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage findnum(@RequestBody Orders orders){
        return ordersService.findnum(orders);
    }

    @PostMapping(value="/findOders",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage findOders(@RequestBody findOdersDto findOdersDtos){
        if(!StringUtils.isNotBlank(findOdersDtos.getOrder_type())){
            findOdersDtos.setState(1);
        }
        else if(findOdersDtos.getOrder_type().equals("pay")){
            findOdersDtos.setState(1);
        }
        else if(findOdersDtos.getOrder_type().equals("deliver")){
            findOdersDtos.setState(2);
        }
        else if(findOdersDtos.getOrder_type().equals("receive")){
            findOdersDtos.setState(3);
        }
        else  if(findOdersDtos.getOrder_type().equals("finish")){
            findOdersDtos.setState(4);
        }
        return ordersService.findOders(findOdersDto.getWhereClause(findOdersDtos));
    }


    @PostMapping(value="/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage delete(@RequestBody Orders orders){
        return ordersService.delete(orders);
    }

    @PostMapping(value="/showall",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage showall(@RequestBody Orders orders){
        return ordersService.showall(orders);
    }

    @PostMapping(value="/pcShow",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage pcShow(@RequestBody Map<String, Object> models){
        fail = "分页查找订单失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Manage>>();
        try{
            SearchOrdersDto searchOrdersDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("searchOrdersDto"), SearchOrdersDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return ordersService.pcShow(SearchOrdersDto.getWhereClause(searchOrdersDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }

    }
}
