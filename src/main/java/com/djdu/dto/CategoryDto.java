package com.djdu.dto;

import com.djdu.common.ShowOut;
import com.djdu.common.Usable;

/**
 * @ClassName CategoryDto
 * @Description Category的DTO
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
public class CategoryDto {

    String category_id;//分类的id

    String name;//分类名

    Usable usable;//分类是否被删除

    ShowOut showOut;//是否展示


    public String getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public Usable getUsable() {
        return usable;
    }

    public ShowOut getShowOut() {
        return showOut;
    }



    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsable(Usable usable) {
        this.usable = usable;
    }

    public void setShowOut(ShowOut showOut) {
        this.showOut = showOut;
    }


    /**
     * @Author DJDU
     * @Description 重写toString
     * @Date 2019/1/26 21:26
     * @Param []
     * @return java.lang.String
     **/
    public String toString(){
        return "category_id:"+category_id+" "+"name:"+name+" "+"usable"+usable+" "+"showOut:"+showOut;
    }
}
