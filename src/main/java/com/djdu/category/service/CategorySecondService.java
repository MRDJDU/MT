package com.djdu.category.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.category.entity.CategoryFirst;
import com.djdu.category.entity.CategorySecond;
import com.djdu.category.repository.CategoryFirstRepository;
import com.djdu.category.repository.CategorySecondRepository;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Base64ToImg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName CategorySecondService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/4/2 8:51
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class CategorySecondService {
    @Autowired
    CategoryFirstRepository categoryFirstRepository;
    @Autowired
    CategorySecondRepository categorySecondRepository;
    @Autowired
    CategoryThirdService categoryThirdService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增二级分类
     * @Date 2019/4/9 19:01
     * @Param [categorySecond]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addCategorySecond(CategorySecond categorySecond){
        ResponseMessage responseMessage = new ResponseMessage<CategorySecond>();
        success = "二级分类新增成功";
        fail = "二级分类新增失败";
        try {
            if (categorySecondRepository.UnDeletedfindExistsName(categorySecond.getName(),categorySecond.getCategoryFirst_id()) > 0) {
                responseMessage.makeFail(fail, "已存在同名的二级分类");
                return responseMessage;
            }
            if (StringUtils.isNotBlank(categorySecond.getImg())) {
                String str = categorySecond.getImg().substring(categorySecond.getImg().indexOf(",") + 1);
                String imagePath = "E:\\workspace\\IdeaProjects\\MT\\MT后台界面\\mtmanager\\static\\categorySecondImg/" + categorySecond.getCategorySecond_id() + ".png";
                Base64ToImg.GenerateImage(str, imagePath);
                categorySecond.setImg(categorySecond.getCategorySecond_id());
            }
            else {
                responseMessage.makeFail(fail, "请选择分类图片");
                return responseMessage;
            }
            categorySecond.setCreatTime(new Date());
            categorySecond.setUsable(Usable.UnDeleted);
            categorySecond.setShowOut(ShowOut.UnShow);
            categorySecondRepository.save(categorySecond);
            responseMessage.makeSuccess(success, categorySecond.getName(), categorySecond);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 分页查找二级分类
     * @Date 2019/4/9 14:13
     * @Param [specification, pageable]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage getCategorySecondPage(Specification specification, Pageable pageable){
        success = "分页查找二级分类成功！";
        fail = "分页查找二级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategorySecond>>();
        try{
            responseMessage.makeSuccess(success,categorySecondRepository.findAll(specification,pageable));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


    /**
     * @Author DJDU
     * @Description TODO 冻结与启用分类
     * @Date 2019/4/11 3:19
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage changesShowOut(CategorySecond categorySecond){
        ResponseMessage responseMessage = new ResponseMessage<CategorySecond>();
        try{
            CategorySecond categorySecond1=categorySecondRepository.findById(categorySecond.getCategorySecond_id()).get();
            if(categorySecond1.getShowOut()== ShowOut.Show){
                success = "分类冻结成功！";
                fail = "分类冻结失败！";
                categorySecond1.setShowOut(ShowOut.UnShow);
            }
            else {
                success = "分类展示成功！";
                fail = "分类展示失败！";
                categorySecond1.setShowOut(ShowOut.Show);
            }
            categorySecondRepository.save(categorySecond1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 删除分类
     * @Date 2019/4/11 3:24
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage delete(CategorySecond categorySecond){
        ResponseMessage responseMessage = new ResponseMessage<CategorySecond>();
        try{
            CategorySecond categorySecond1=categorySecondRepository.findById(categorySecond.getCategorySecond_id()).get();
            success = "分类删除成功！";
            fail = "分类删除失败！";
            categorySecond1.setUsable(Usable.Deleted);
            categorySecondRepository.save(categorySecond1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
