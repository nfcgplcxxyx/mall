package com.jcfx.mall.order.dao;

import com.jcfx.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ????
 * 
 * @author Bob Shaw
 * @email hninee@163.com
 * @date 2022-12-21 10:06:19
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
