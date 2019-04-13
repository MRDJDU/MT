package com.djdu.category.service;

import com.djdu.category.entity.CategorySecond;
import com.djdu.category.entity.CategoryThird;
import com.djdu.category.repository.CategoryFirstRepository;
import com.djdu.category.repository.CategorySecondRepository;
import com.djdu.category.repository.CategoryThirdRepository;
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

/**
 * @ClassName CategoryThirdService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/4/2 8:51
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class CategoryThirdService {
    @Autowired
    CategoryFirstRepository categoryFirstRepository;
    @Autowired
    CategorySecondRepository categorySecondRepository;
    @Autowired
    CategoryThirdRepository categoryThirdRepository;
    private String success = "";
    private String fail = "";


    /**
     * @Author DJDU
     * @Description TODO 新增三级分类
     * @Date 2019/4/9 19:04
     * @Param [categoryThird]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addCategoryThird(CategoryThird categoryThird){
        ResponseMessage responseMessage = new ResponseMessage<CategoryThird>();
        success = "三级分类新增成功";
        fail = "三级分类新增失败";
        try {
            if (categoryThirdRepository.UnDeletedfindExistsName(categoryThird.getName(),categoryThird.getCategorySecond_id()) > 0) {
                responseMessage.makeFail(fail, "已存在同名的三级分类");
                return responseMessage;
            }
            if (StringUtils.isNotBlank(categoryThird.getImg())) {
                String str = categoryThird.getImg().substring(categoryThird.getImg().indexOf(",") + 1);
                String imagePath = "E:\\workspace\\IdeaProjects\\MT\\MT后台界面\\mtmanager\\static\\categoryThirdImg/" + categoryThird.getCategoryThird_id() + ".png";
                Base64ToImg.GenerateImage(str, imagePath);
                categoryThird.setImg(categoryThird.getCategoryThird_id());
            }
            else {
                responseMessage.makeFail(fail, "请选择分类图片");
                return responseMessage;
            }
            categoryThird.setCreatTime(new Date());
            categoryThird.setUsable(Usable.UnDeleted);
            categoryThird.setShowOut(ShowOut.UnShow);
            categoryThirdRepository.save(categoryThird);
            responseMessage.makeSuccess(success, categoryThird.getName(), categoryThird);
            return responseMessage;
        } catch (Exception e) {
            responseMessage.makeError(fail, e.getMessage());
            return responseMessage;
        }
    }



    public ResponseMessage getCategoryThirdPage(Specification specification, Pageable pageable){
        success = "分页查找三级分类成功！";
        fail = "分页查找三级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategoryThird>>();
        try{
            responseMessage.makeSuccess(success,categoryThirdRepository.findAll(specification,pageable));
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
    public ResponseMessage changesShowOut(CategoryThird categoryThird){
        ResponseMessage responseMessage = new ResponseMessage<CategoryThird>();
        try{
            CategoryThird categoryThird1=categoryThirdRepository.findById(categoryThird.getCategoryThird_id()).get();
            if(categoryThird1.getShowOut()== ShowOut.Show){
                success = "分类冻结成功！";
                fail = "分类冻结失败！";
                categoryThird1.setShowOut(ShowOut.UnShow);
            }
            else {
                success = "分类展示成功！";
                fail = "分类展示失败！";
                categoryThird1.setShowOut(ShowOut.Show);
            }
            categoryThirdRepository.save(categoryThird1);
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
    public ResponseMessage delete(CategoryThird categoryThird){
        ResponseMessage responseMessage = new ResponseMessage<CategoryThird>();
        try{
            CategoryThird categoryThird1=categoryThirdRepository.findById(categoryThird.getCategoryThird_id()).get();
            success = "分类删除成功！";
            fail = "分类删除失败！";
            categoryThird1.setUsable(Usable.Deleted);
            categoryThirdRepository.save(categoryThird1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


}
