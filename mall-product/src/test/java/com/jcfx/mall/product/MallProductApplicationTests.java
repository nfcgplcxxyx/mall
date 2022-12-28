package com.jcfx.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcfx.mall.product.entity.BrandEntity;
import com.jcfx.mall.product.entity.CategoryEntity;
import com.jcfx.mall.product.service.BrandService;
import com.jcfx.mall.product.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void contextLoads() {
        BrandEntity brand = new BrandEntity();
        brand.setDescript("中华有为");
        brand.setLogo("菊花");
        brand.setName("华为");
        brand.setFirstLetter("H");
        brand.setShowStatus(1);
        brand.setSort(1);
        brandService.save(brand);
    }

    @Test
    public void selectList() {
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("1", 1));
        list.forEach((item) -> {
            System.out.println(item);
        });
    }

    @Test
    public void categoryTest() {
        List<CategoryEntity> entities = categoryService.treeList();
        entities.forEach(item -> System.out.println(item));
    }



}
