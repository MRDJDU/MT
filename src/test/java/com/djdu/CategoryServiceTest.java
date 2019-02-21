package com.djdu;

import com.djdu.category.entity.Category;
import com.djdu.category.service.CategoryService;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @ClassName CategoryServiceTest
 * @Description Category的单元测试类
 * @Author DJDU
 * @Date 2019/1/27 18:31
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void save(){
        //一级分类
        Category computer = new Category();
        Category cellphone = new Category();
        Category  maintain = new Category();
        //二级分类
        Category hot = new Category();
        Category computerMake = new Category();
        //三级分类
        Category prower = new Category();
        Category NokeBook = new Category();

        maintain.setCategory_id(UUID.randomUUID().toString());
        maintain.setName("维修");
        maintain.setShowOut(ShowOut.UnShow);
        maintain.setUsable(Usable.UnDeleted);
        maintain.setGrade(1);
        maintain.setMorder(1);

        computer.setCategory_id(UUID.randomUUID().toString());
        computer.setName("电脑");
        computer.setShowOut(ShowOut.UnShow);
        computer.setUsable(Usable.UnDeleted);
        computer.setGrade(1);
        computer.setMorder(2);

        cellphone.setCategory_id(UUID.randomUUID().toString());
        cellphone.setName("手机");
        cellphone.setShowOut(ShowOut.UnShow);
        cellphone.setUsable(Usable.UnDeleted);
        cellphone.setGrade(1);
        cellphone.setMorder(3);

        hot.setCategory_id(UUID.randomUUID().toString());
        hot.setName("热门商品");
        hot.setShowOut(ShowOut.UnShow);
        hot.setUsable(Usable.UnDeleted);
        hot.setGrade(2);
        hot.setMorder(1);

        computerMake.setCategory_id(UUID.randomUUID().toString());
        computerMake.setName("电脑整机");
        computerMake.setShowOut(ShowOut.UnShow);
        computerMake.setUsable(Usable.UnDeleted);
        computerMake.setGrade(2);
        computerMake.setMorder(2);

        prower.setCategory_id(UUID.randomUUID().toString());
        prower.setName("移动电源");
        prower.setShowOut(ShowOut.UnShow);
        prower.setUsable(Usable.UnDeleted);
        prower.setGrade(3);
        prower.setMorder(1);

        NokeBook.setCategory_id(UUID.randomUUID().toString());
        NokeBook.setName("笔记本");
        NokeBook.setShowOut(ShowOut.UnShow);
        NokeBook.setUsable(Usable.UnDeleted);
        NokeBook.setGrade(3);
        NokeBook.setMorder(2);

        hot.addChildCategory(prower);
        computerMake.addChildCategory(NokeBook);

        computer.addChildCategory(hot);
        computer.addChildCategory(computerMake);

        categoryService.save(maintain);
        categoryService.save(computer);
        categoryService.save(cellphone);

    }
}
