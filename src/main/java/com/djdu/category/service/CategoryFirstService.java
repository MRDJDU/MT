package com.djdu.category.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.category.entity.CategoryFirst;
import com.djdu.category.repository.CategoryFirstRepository;
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

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName CategoryFirstService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/4/2 8:50
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class CategoryFirstService {
    @Autowired
    CategoryFirstRepository categoryFirstRepository;
    CategorySecondService categorySecondService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增一级分类
     * @Date 2019/4/2 22:45
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addCategoryFirst(CategoryFirst categoryFirst) {
        ResponseMessage responseMessage = new ResponseMessage<CategoryFirst>();
        success = "一级分类新增成功";
        fail = "一级分类新增失败";
        try {
            if (categoryFirstRepository.UnDeletedfindExistsName(categoryFirst.getName()) > 0) {
                responseMessage.makeFail(fail, "已存在同名的一级分类");
                return responseMessage;
            }
            if (StringUtils.isNotBlank(categoryFirst.getImg())) {
                String str = categoryFirst.getImg().substring(categoryFirst.getImg().indexOf(",") + 1);
                String imagePath = "E:\\workspace\\IdeaProjects\\MT\\MT后台界面\\mtmanager\\static\\categoryFirstImg/" + categoryFirst.getCategoryFirst_id() + ".png";
                Base64ToImg.GenerateImage(str, imagePath);
                categoryFirst.setImg(categoryFirst.getCategoryFirst_id());
            }
            else {
                responseMessage.makeFail(fail, "请选择分类图片");
                return responseMessage;
            }
            categoryFirst.setCreatTime(new Date());
            categoryFirst.setUsable(Usable.UnDeleted);
            categoryFirst.setShowOut(ShowOut.UnShow);
            categoryFirstRepository.save(categoryFirst);
            responseMessage.makeSuccess(success, categoryFirst.getName(), categoryFirst);
            return responseMessage;

        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 分页查找一级分类
     * @Date 2019/4/9 11:15
     * @Param [specification, pageable]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage getCategoryFirstPage(Specification specification, Pageable pageable){
        success = "分页查找一级分类成功！";
        fail = "分页查找一级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategoryFirst>>();
        try{
            responseMessage.makeSuccess(success,categoryFirstRepository.findAll(specification,pageable));
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
    public ResponseMessage changesShowOut(CategoryFirst categoryFirst){
        ResponseMessage responseMessage = new ResponseMessage<CategoryFirst>();
        try{
            CategoryFirst categoryFirst1=categoryFirstRepository.findById(categoryFirst.getCategoryFirst_id()).get();
            if(categoryFirst1.getShowOut()== ShowOut.Show){
                success = "分类冻结成功！";
                fail = "分类冻结失败！";
                categoryFirst1.setShowOut(ShowOut.UnShow);
            }
            else {
                success = "分类展示成功！";
                fail = "分类展示失败！";
                categoryFirst1.setShowOut(ShowOut.Show);
            }
            categoryFirstRepository.save(categoryFirst1);
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
    public ResponseMessage delete(CategoryFirst categoryFirst){
        ResponseMessage responseMessage = new ResponseMessage<CategoryFirst>();
        try{
            CategoryFirst categoryFirst1=categoryFirstRepository.findById(categoryFirst.getCategoryFirst_id()).get();
            success = "分类删除成功！";
            fail = "分类删除失败！";
            categoryFirst1.setUsable(Usable.Deleted);
            categoryFirstRepository.save(categoryFirst1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
