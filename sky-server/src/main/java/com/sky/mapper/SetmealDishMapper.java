package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.nio.file.LinkOption;
import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * Desc: 根据菜品ID查询对应的套餐id
     * @param DishIds
     * @return {@link java.util.List<java.lang.Long>}
     * @author L_Misaki
     */
    List<Long> getSetmealIdsByDishIds(List<Long> DishIds);
}
