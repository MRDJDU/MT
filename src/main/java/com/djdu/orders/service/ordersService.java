package com.djdu.orders.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.goods.dto.ShowGoods;
import com.djdu.goods.entity.Goods;
import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.orderDetails.repository.OrderDetailsRepository;
import com.djdu.orders.dto.*;
import com.djdu.orders.entity.Orders;
import com.djdu.orders.entity.ordersStaters;
import com.djdu.orders.repository.OrderRepository;
import com.djdu.shoppingCart.dto.ShoppingCartDto;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName ordersService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 14:43
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class ordersService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    private String success = "";
    private String fail = "";

    public ResponseMessage addorder(addOderDto addOderDtos){
        fail="订单添加失败";
        success="订单添加成功";
        ResponseMessage responseMessage = new ResponseMessage<Orders>();
        Orders orders =new Orders();
        orders.setItemCount(addOderDtos.getOrderDetails().size());
        orders.setPriceCount(addOderDtos.getTotal());
        orders.setState(1);
        orders.setUser_id(addOderDtos.getUser_id());
        orders.setCreatTime(new Date());
        orders.setAddress_xq(addOderDtos.getAdd().getAddress_xq());
        orders.setName(addOderDtos.getAdd().getName());
        orders.setTel(addOderDtos.getAdd().getTel());
        orders.setOrders_id(UUID.randomUUID().toString());
        for(int i = 0 ; i < addOderDtos.getOrderDetails().size();i++){
            OrderDetails orderDetails = addOderDtos.getOrderDetails().get(i);
            orderDetails.setOrderDetails_id(UUID.randomUUID().toString());
            orderDetails.setOrders_id(orders.getOrders_id());
            orderDetails.setCreatTime(new Date());
            orderDetailsRepository.save(orderDetails);

            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setShoppingCart_id(orderDetails.getId());
            shoppingCartDto.setUser_id(addOderDtos.getUser_id());
            ShoppingCart shoppingCart = (ShoppingCart)shoppingCartRepository.findAll(ShoppingCartDto.getWhereClause(shoppingCartDto)).get(0);
            shoppingCartRepository.deleteById(shoppingCart.getId());
        }
        orderRepository.save(orders);
        responseMessage.makeSuccess(success,orders);
        return responseMessage;
    }

    public ResponseMessage changeorder(Orders orders){
        fail="订单添加失败";
        success="订单添加成功";
        ResponseMessage responseMessage = new ResponseMessage<String>();
        Orders orders1 = orderRepository.findById(orders.getOrders_id()).get();
        orders1.setState(orders.getState());
        orderRepository.save(orders1);
        responseMessage.makeSuccess(success);
        return responseMessage;
    }

    public ResponseMessage findnum(Orders orders){
        fail="订单添加失败";
        success="订单添加成功";
        ResponseMessage responseMessage = new ResponseMessage<ordersStaters>();
        ordersStaters ordersStaters = new ordersStaters();
        ordersStaters.setFinish_num(orderRepository.Finish_num(orders.getUser_id()));
        ordersStaters.setPay_num(orderRepository.Pay_num(orders.getUser_id()));
        ordersStaters.setRec_num(orderRepository.Rec_num(orders.getUser_id()));
        responseMessage.makeSuccess(success,ordersStaters);
        return responseMessage;
    }

    public ResponseMessage findOders(Specification specification){
        success = "查找成功！";
        fail = "查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<List<showFindOders>>();
        try{
            List<Orders> orderss = orderRepository.findAll(specification);
            List<showFindOders> showFindOderss = Arrays.asList(new showFindOders[orderss.size()]);
            for (int i = 0 ; i < orderss.size(); i++){
                showFindOders showFindOdersa = new showFindOders();
                showFindOdersa.setId(orderss.get(i).getOrders_id());
                showFindOdersa.setPrice(orderss.get(i).getPriceCount());
                showFindOdersa.setPro_count(orderss.get(i).getItemCount());
                findByOrderToODto findByOrderToODtos = new findByOrderToODto();
                findByOrderToODtos.setOrders_id(orderss.get(i).getOrders_id());
                OrderDetails orderDetails = orderDetailsRepository.findAll(findByOrderToODto.getWhereClause(findByOrderToODtos)).get(0);
                showFindOdersa.setProduct_num(orderDetails.getNum());
                showFindOdersa.setPrice_yh(orderDetails.getPrice());
                showFindOdersa.setName(orderDetails.getName());
                showFindOdersa.setPhoto_x(orderDetails.getPhoto_x());
                showFindOderss.set(i,showFindOdersa);
            }


            responseMessage.makeSuccess(success,showFindOderss);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


    public ResponseMessage delete(Orders orders){
        fail="订单添加失败";
        success="订单添加成功";
        ResponseMessage responseMessage = new ResponseMessage<String>();
        orderRepository.deleteById(orders.getOrders_id());
        responseMessage.makeSuccess(success);
        return responseMessage;
    }

    public ResponseMessage showall(Orders orders){
        fail="查看详情失败";
        success="查看详情成功";
        ResponseMessage responseMessage = new ResponseMessage<ShowshowDto>();
        Orders orders1 = orderRepository.findById(orders.getOrders_id()).get();
        findByOrderToODto findByOrderToODtos = new findByOrderToODto();
        findByOrderToODtos.setOrders_id(orders.getOrders_id());
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll(findByOrderToODto.getWhereClause(findByOrderToODtos));
        ShowshowDto showshowDto = new ShowshowDto();
        ord ords = new ord();
        ords.setAddress_xq(orders1.getAddress_xq());
        ords.setReceiver(orders1.getName());
        ords.setTel(orders1.getTel());
        ords.setAddtime(orders1.getCreatTime());
        ords.setId(orders1.getOrders_id());
        ords.setOrder_sn(orders1.getOrders_id());
        if(orders1.getState()==1){
            ords.setOrder_status("待付款");
        }
        else if(orders1.getState()==2){
            ords.setOrder_status("待发货");
        }
        else if(orders1.getState()==3){
            ords.setOrder_status("待收货");
        }
        else if(orders1.getState()==4){
            ords.setOrder_status("已完成");
        }
        ords.setPrice(orders1.getPriceCount());
        List<pro> pro = Arrays.asList(new pro[orderDetailsList.size()]);
        for(int i = 0 ;i < orderDetailsList.size();i++){
            pro pro1 = new pro();
            pro1.setName(orderDetailsList.get(i).getName());
            pro1.setNum(orderDetailsList.get(i).getNum());
            pro1.setPhoto_x(orderDetailsList.get(i).getPhoto_x());
            pro1.setPrice(orderDetailsList.get(i).getPrice());
            pro.set(i,pro1);
        }
        showshowDto.setOrd(ords);
        showshowDto.setPros(pro);

        responseMessage.makeSuccess(success,showshowDto);
        return responseMessage;
    }

    public ResponseMessage pcShow(Specification specification, Pageable pageable){
        success = "分页查找订单成功！";
        fail = "分页查找订单失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<PcShowDto>>();
        try{
            Page<Orders> page =orderRepository.findAll(specification,pageable);
            if(page.getContent()!=null){
                List<PcShowDto> ordersList =  Arrays.asList(new PcShowDto[page.getContent().size()]);
                for (int i = 0;i < page.getContent().size();i++){
                    PcShowDto pcShowDto = new PcShowDto();
                    Orders orders = page.getContent().get(i);
                    pcShowDto.setOrders(orders);

                    findByOrderToODto findByOrderToODtos = new findByOrderToODto();
                    findByOrderToODtos.setOrders_id(orders.getOrders_id());
                    List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll(findByOrderToODto.getWhereClause(findByOrderToODtos));
                    pcShowDto.setOrderDetailsList(orderDetailsList);

                    ordersList.set(i,pcShowDto);
                }
                Page<PcShowDto> page1 = new PageImpl(ordersList, pageable,page.getTotalElements());
                responseMessage.makeSuccess(success,page1);
            }
            else{
                responseMessage.makeSuccess(success,null);
            }
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


}
