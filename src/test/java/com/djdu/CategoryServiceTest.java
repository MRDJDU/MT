package com.djdu;

import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.entity.Category;
import com.djdu.service.CategoryService;
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
        Category category = new Category();
        for (int i=1;i<=10;i++){
            category.setCategory_id(UUID.randomUUID().toString());
            category.setName("DJDU"+i);
            category.setShowOut(ShowOut.UnShow);
            category.setUsable(Usable.UnDeleted);
            categoryService.save(category);
        }
    }
}
