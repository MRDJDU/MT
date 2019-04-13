package com.djdu.brand.service;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.brand.repository.BrandRepository;
import com.djdu.category.entity.CategoryFirst;
import com.djdu.category.entity.CategorySecond;
import com.djdu.category.repository.CategoryFirstRepository;
import com.djdu.category.repository.CategorySecondRepository;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName BrandService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理,有两个冲突点，没删除的品牌name不能重复，品牌是逻辑删除的
 * @Author DJDU
 * @Date 2019/2/21 9:51
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class BrandService {
    @Autowired
    BrandRepository brandRepository;//注入品牌仓库层
    @Autowired
    CategoryFirstRepository categoryFirstRepository;
    @Autowired
    CategorySecondRepository categorySecondRepository;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增品牌实体，品牌名不能重复（在所有未删除的数据中）
     * @Date 2019/2/21 10:00
     * @Param [brand]
     * @return void
     **/
    public ResponseMessage addBrand(Brand brand){
        fail="新增品牌失败";
        success="新增品牌成功";
        ResponseMessage responseMessage = new ResponseMessage<Brand>();
        if(brand.getName()==null || brand.getName()==""){
            responseMessage.makeFail(fail,"品牌名不能为空");
            return responseMessage;
        }
        List<String> a = brand.getCategory_ids();
        String b ="";
        String c ="";
        if(a.size()>1){
            b = a.get(1);
            c = a.get(0);
            CategoryFirst categoryFirst = categoryFirstRepository.findById(c).get();
            CategorySecond categorySecond = categorySecondRepository.findById(b).get();
            brand.setCategory_name(categoryFirst.getName() + " / " + categorySecond.getName());
            brand.setCategory_id(categoryFirst.getCategoryFirst_id() + "/" + categorySecond.getCategorySecond_id());
        }else{
            b=a.get(0);
            CategoryFirst categoryFirst = categoryFirstRepository.findById(b).get();
            brand.setCategory_name(categoryFirst.getName());
            brand.setCategory_id(categoryFirst.getCategoryFirst_id());
        }
        if(brandRepository.UnDeletedfindExistsName(brand.getName(),b)>0){
            responseMessage.makeFail(fail,"所属分类品牌名已存在！");
            return responseMessage;
        }
        else {
            try{
                brand.setBrand_id(UUID.randomUUID().toString());
                brand.setCreatTime(new Date());
                brand.setUsable(Usable.UnDeleted);
                brand.setShowOut(ShowOut.UnShow);
                brandRepository.save(brand);
                responseMessage.makeSuccess(success,brand.getName(),brand);
                return responseMessage;
            }catch (Exception e){
                responseMessage.makeError(fail,e.getMessage());
                return responseMessage;
            }
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 修改（未删除）品牌信息，品牌名不能重复（在所有未删除的数据中）
     * @Date 2019/2/26 8:57
     * @Param [brandDto]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage editBrand(BrandDto brandDto){
        success = "品牌修改成功！";
        fail = "修改品牌失败！";
        ResponseMessage responseMessage = new ResponseMessage<Brand>();
        if (brandDto.getBrand_id()==null || brandDto.getBrand_id()=="") {
            responseMessage.makeFail(fail,"品牌id不能为空！");
            return responseMessage;
        }
        Brand brand = brandRepository.findById(brandDto.getBrand_id()).get();
        if(brand==null || brand.getUsable()==Usable.Deleted){
            responseMessage.makeFail(fail,"查询数据库无该品牌！");
            return responseMessage;
        }
        else
        {
            if(brandDto.getName()!=null){
                if(brandRepository.UnDeletedfindExistsName(brandDto.getName(),brand.getCategory_id())>0 && !brandDto.getBrand_id().equals(brandRepository.UnDeletedfindNameGetId(brandDto.getName()))){
                    responseMessage.makeFail(fail,"所属分类品牌名已存在！");
                    return responseMessage;
                }
            }
            try{
                BeanUtils.copyProperties(brandDto,brand);
                brand.setUpdateTime(new Date());
                brandRepository.save(brand);
                responseMessage.makeSuccess(success,brand.getName(),brand);
                return responseMessage;
            }catch (Exception e){
                responseMessage.makeError(fail,e.getMessage());
                return responseMessage;
            }
        }
    }
    

    /**
     * @Author DJDU
     * @Description TODO 批量操作品牌(未删除)显示在客户端
     * @Date 2019/2/26 8:42 
     * @Param [brand_ids, showOut]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage showOutAll(List<String> brand_ids,ShowOut showOut){
        ResponseMessage responseMessage = new ResponseMessage<List<Brand>>();
        try{
            List<Brand> brands = (List<Brand>)brandRepository.findAllById(brand_ids);
            for (Brand brand : brands) {
                    if(brand.getUsable()!=Usable.Deleted){
                        brand.setShowOut(showOut);
                        brand.setUpdateTime(new Date());
                        brandRepository.save(brand);
                    }
            }
            responseMessage.setStatuCode(true);
            if(showOut==ShowOut.Show){
                success = "批量展示品牌成功！";
                responseMessage.makeSuccess(success);
            }
            else{
                success = "批量隐藏品牌成功！";
                responseMessage.makeSuccess(success);
            }
            return responseMessage;
        }
        catch (Exception e){
            if(showOut==ShowOut.Show){
                fail = "批量展示品牌失败";
                responseMessage.makeError(fail,e.getMessage());
            }
            else{
                fail = "批量隐藏品牌失败";
                responseMessage.makeError(fail,e.getMessage());
            }
            return responseMessage;
        }
    }

    public ResponseMessage showOut(Brand brand){
        ResponseMessage responseMessage = new ResponseMessage<List<Brand>>();
        try{
            Brand brand1 = brandRepository.findById(brand.getBrand_id()).get();
            if(brand1.getShowOut() == ShowOut.Show){
                brand1.setShowOut(ShowOut.UnShow);
                brand1.setUpdateTime(new Date());
                brandRepository.save(brand1);
                success = "冻结品牌成功！";
                responseMessage.makeSuccess(success);
            }
            else{
                brand1.setShowOut(ShowOut.Show);
                brand1.setUpdateTime(new Date());
                brandRepository.save(brand1);
                success = "显示品牌成功！";
                responseMessage.makeSuccess(success);
            }
            return responseMessage;
        }
        catch (Exception e){
            fail = "操作品牌失败";
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }



    public ResponseMessage getBrandPage(Specification specification, Pageable pageable){
        success = "分页查找成功！";
        fail = "分页查找失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Brand>>();
        try{
            responseMessage.makeSuccess(success,brandRepository.findAll(specification,pageable));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }

    }

    /**
     * @Author DJDU
     * @Description TODO 批量删除
     * @Date 2019/4/12 0:56
     * @Param [ids]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage deleteBrands(List<String> ids){
        success = "品牌批量删除成功！";
        fail = "修改批量删除失败！";
        ResponseMessage responseMessage = new ResponseMessage<String[]>();
        try{
            List<Brand> brands = (List<Brand>)brandRepository.findAllById(ids);
            for (Brand brand : brands) {
                brand.setUsable(Usable.Deleted);
                brand.setUpdateTime(new Date());
                brandRepository.save(brand);
            }
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
