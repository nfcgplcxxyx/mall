package com.jcfx.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.Query;
import com.jcfx.mall.coupon.dao.HomeSubjectSpuDao;
import com.jcfx.mall.coupon.entity.HomeSubjectSpuEntity;
import com.jcfx.mall.coupon.service.HomeSubjectSpuService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageUtils(page);
    }

}