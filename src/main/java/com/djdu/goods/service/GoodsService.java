package com.djdu.goods.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.brand.entity.Brand;
import com.djdu.brand.repository.BrandRepository;
import com.djdu.category.repository.CategoryFirstRepository;
import com.djdu.category.repository.CategorySecondRepository;
import com.djdu.category.repository.CategoryThirdRepository;
import com.djdu.common.Enums.ImageCategory;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Base64ToImg;
import com.djdu.goods.dto.AddgoodsDto;
import com.djdu.goods.dto.ShowGoods;
import com.djdu.goods.dto.detailsImageDto;
import com.djdu.goods.entity.Goods;
import com.djdu.goods.repository.GoodsRepository;
import com.djdu.imageResource.dto.ImageResourceDto;
import com.djdu.imageResource.entity.ImageResource;
import com.djdu.imageResource.repository.ImageResourceRepository;
import com.djdu.norms.dto.NormsDto;
import com.djdu.norms.entity.Norms;
import com.djdu.norms.repository.NormsRepository;
import com.djdu.sku.entity.SKU;
import com.djdu.sku.entity.SKUValue;
import com.djdu.sku.repository.GroupRepository;
import com.djdu.sku.repository.SKURepository;
import com.djdu.sku.repository.SKUValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * @ClassName GoodsService
 * @Description TODO 商品添加逻辑层，涉及其他类逻辑层--selectedaddOptions（分类）、selectedaddBrandOptions（品牌）、图片资源、基础属性、SKU
 * @Author DJDU
 * @Date 2019/4/14 22:50
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;//商品持久层
    @Autowired
    ImageResourceRepository imageResourceRepository;//图片资源持久层
    @Autowired
    NormsRepository normsRepository;//基础属性持久层
    @Autowired
    SKURepository skuRepository;//库存计量单位属性持久层
    @Autowired
    SKUValueRepository skuValueRepository;//库存计量单位属性值持久层
    @Autowired
    GroupRepository groupRepository;//库存计量单位属性组别持久层
    @Autowired
    CategoryFirstRepository categoryFirstRepository;
    @Autowired
    CategorySecondRepository categorySecondRepository;
    @Autowired
    CategoryThirdRepository categoryThirdRepository;
    @Autowired
    BrandRepository brandRepository;


    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增商品
     * @Date 2019/4/16 1:48
     * @Param [addgoodsDto]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addGoods(AddgoodsDto addgoodsDto){
        fail="新增商品失败";
        success="新增商品成功";
        ResponseMessage responseMessage = new ResponseMessage<String>();
        try{
            // 填写goods实体
            Goods goods = new Goods();
            goods.setGoods_id(UUID.randomUUID().toString());
            goods.setBrand_id(addgoodsDto.getSelectedaddBrandOptions().get(0));
            goods.setCategory_id(addgoodsDto.getSelectedaddOptions().get(0) + '/' + addgoodsDto.getSelectedaddOptions().get(1) + '/' + addgoodsDto.getSelectedaddOptions().get(2));
            goods.setName(addgoodsDto.getGoodsname());
            goods.setCreatTime(new Date());
            goods.setShowOut(ShowOut.UnShow);
            goods.setUsable(Usable.UnDeleted);


            //填写norms实体
            List<Norms> norms =  Arrays.asList(new Norms[addgoodsDto.getNormsDtos().size()]);
            for(int i = 0;i<addgoodsDto.getNormsDtos().size();i++){
                Norms norms1 = new Norms();
                norms1.setNorms_id(UUID.randomUUID().toString());
                norms1.setName(addgoodsDto.getNormsDtos().get(i).getName());
                norms1.setValue(addgoodsDto.getNormsDtos().get(i).getValue());
                norms1.setGoods_id(goods.getGoods_id());
                norms1.setCreatTime(new Date());
                norms.set(i,norms1);
            }

            //填写img资源主图
            ImageResource imageResource = new ImageResource();
            imageResource.setImageResource_id(UUID.randomUUID().toString());
            imageResource.setImageCategory(ImageCategory.MasterImage);
            imageResource.setCreatTime(new Date());
            imageResource.setGoods_id(goods.getGoods_id());
                String str = addgoodsDto.getShowcropImgs().get(0).substring(addgoodsDto.getShowcropImgs().get(0).indexOf(",")+1);
                String imagePath = "E:\\workspace\\IdeaProjects\\MT\\MT后台界面\\mtmanager\\static\\ImageResource/"+imageResource.getImageResource_id()+".png";
                Base64ToImg.GenerateImage(str,imagePath);
            imageResource.setImageURL(imageResource.getImageResource_id());


            //填写img详情主图
            List<ImageResource> imageResources =  Arrays.asList(new ImageResource[addgoodsDto.getOthercropImgs().size()]);
            for(int i = 0;i < addgoodsDto.getOthercropImgs().size();i++){
                ImageResource imageResource1 = new ImageResource();
                imageResource1.setImageResource_id(UUID.randomUUID().toString());
                imageResource1.setImageCategory(ImageCategory.DetailsImage);
                imageResource1.setCreatTime(new Date());
                imageResource1.setGoods_id(goods.getGoods_id());
                    String strs = addgoodsDto.getOthercropImgs().get(i).substring(addgoodsDto.getOthercropImgs().get(i).indexOf(",")+1);
                    String imagePaths = "E:\\workspace\\IdeaProjects\\MT\\MT后台界面\\mtmanager\\static\\ImageResource/"+imageResource1.getImageResource_id()+".png";
                    Base64ToImg.GenerateImage(strs,imagePaths);
                imageResource1.setImageURL(imageResource1.getImageResource_id());
                imageResources.set(i,imageResource1);
            }


            ////填写SKU
            //List<SKU> skus =  Arrays.asList(new SKU[addgoodsDto.getSkuDtos().size()]);
            //List<SKUValue> skuValues =  Arrays.asList(new SKUValue[addgoodsDto.getSkuDtos().size()]);
            //String group_id = UUID.randomUUID().toString();
            //for(int i = 0; i < addgoodsDto.getSkuDtos().size(); i++){
            //    SKU sku = new SKU();
            //    SKUValue skuValue = new SKUValue();
            //    sku.setSKU_id(UUID.randomUUID().toString());
            //    sku.setName(addgoodsDto.getSkuDtos().get(i).getName());
            //    sku.setGoods_id(goods.getGoods_id());
            //    skuValue.setSKU_id(sku.getSKU_id());
            //    skuValue.setSKUValue_id(UUID.randomUUID().toString());
            //    skuValue.setValue(addgoodsDto.getSkuDtos().get(i).getValue());
            //    skuValue.setGroup_id(group_id);
            //    skus.set(i,sku);
            //    skuValues.set(i,skuValue);
            //}

            goodsRepository.save(goods);

            for(int i = 0 ; i < norms.size() ; i++){
                normsRepository.save(norms.get(i));
            }

            imageResourceRepository.save(imageResource);

            for(int i = 0 ; i < imageResources.size() ; i++){
                imageResourceRepository.save(imageResources.get(i));
            }

            //for(int i = 0 ; i < skus.size() ; i++){
            //    skuRepository.save(skus.get(i));
            //    skuValueRepository.save(skuValues.get(i));
            //}
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }



    public ResponseMessage getPage(Specification specification, Pageable pageable){
        success = "分页查找商品列表成功！";
        fail = "分页查找商品列表失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<ShowGoods>>();
        try{
            Page<Goods> page = goodsRepository.findAll(specification,pageable);
            if(page.getContent()!=null){
                List<ShowGoods> showGoods =  Arrays.asList(new ShowGoods[page.getContent().size()]);
                for (int i = 0;i < page.getContent().size();i++){
                    ShowGoods showGoods1 = new ShowGoods();
                    Goods goods = page.getContent().get(i);
                    showGoods1.setGoodsName(goods.getName());
                    showGoods1.setGoods_id(goods.getGoods_id());
                    showGoods1.setBrand_id(goods.getBrand_id());
                    showGoods1.setCategory_id(goods.getCategory_id());
                    showGoods1.setCreatTime(goods.getCreatTime());
                    showGoods1.setShowOut(goods.getShowOut());
                    showGoods1.setUsable(goods.getUsable());
                    String[] strs=goods.getCategory_id().split("/");
                    String category = categoryFirstRepository.findByID(strs[0]).get().getName()+"/"+categorySecondRepository.findByID(strs[1]).get().getName()+"/"+categoryThirdRepository.findByID(strs[2]).get().getName();
                    showGoods1.setCategory(category);
                    showGoods1.setBrand(brandRepository.findById(goods.getBrand_id()).get().getName());
                    NormsDto normsDto = new NormsDto();
                    normsDto.setGoods_id(goods.getGoods_id());
                    showGoods1.setNorms(normsRepository.findAll(NormsDto.getWhereClause(normsDto)));
                    ImageResourceDto imageResourceDto = new ImageResourceDto();
                    imageResourceDto.setGoods_id(goods.getGoods_id());
                    imageResourceDto.setImageCategory(ImageCategory.MasterImage);
                    showGoods1.setMasterImage(imageResourceRepository.findAll(ImageResourceDto.getWhereClause(imageResourceDto)).get(0).getImageURL());

                    imageResourceDto.setImageCategory(ImageCategory.DetailsImage);
                    List<ImageResource> imageResources  = imageResourceRepository.findAll(ImageResourceDto.getWhereClause(imageResourceDto));
                    List<detailsImageDto> detailsImageDtos = Arrays.asList(new detailsImageDto[imageResources.size()]);
                    for (int j = 0;j<imageResources.size();j++){
                        detailsImageDto detailsImageDto1 = new detailsImageDto();
                        detailsImageDto1.setImg(imageResources.get(j).getImageURL());
                        detailsImageDtos.set(j,detailsImageDto1);
                    }
                    showGoods1.setDetailsImage(detailsImageDtos);
                    showGoods.set(i,showGoods1);
                }
                Page<ShowGoods> page1 = new PageImpl(showGoods, pageable,page.getTotalElements());
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



    /**
     * @Author DJDU
     * @Description TODO 删除商品
     * @Date 2019/4/18 19:50
     * @Param [ids]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage deteleGoods(List<String> ids){
        success = "品牌批量删除成功！";
        fail = "品牌批量删除失败！";
        ResponseMessage responseMessage = new ResponseMessage<String[]>();
        try{
            List<Goods> goods1 = (List<Goods>)goodsRepository.findAllById(ids);
            for (Goods goods : goods1) {
                goods.setUsable(Usable.Deleted);
                goods.setUpdateTime(new Date());
                goodsRepository.save(goods);
            }
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    public ResponseMessage ShowGoods(Goods goods){
        ResponseMessage responseMessage = new ResponseMessage<Goods>();
        try{
            Goods goods1=goodsRepository.findById(goods.getGoods_id()).get();
            if(goods1.getShowOut()==ShowOut.Show){
                success = "商品下架成功！";
                fail = "商品下架失败！";
                goods1.setShowOut(ShowOut.UnShow);
            }
            else {
                success = "商品上架成功！";
                fail = "商品上架失败！";
                goods1.setShowOut(ShowOut.Show);
            }
            goodsRepository.save(goods1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }



}
