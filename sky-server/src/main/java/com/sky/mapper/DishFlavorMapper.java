package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Desc: 批量插入口味
     * @param flavors
     * @return
     * @author L_Misaki
     */
    void insertBatch(List<DishFlavor> flavors);
}
