package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * Desc: 菜品和对应的口味 
     * @param dishDTO
     * @return 
     * @author L_Misaki
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * Desc: 菜品分页查询 
     * @param dishPageQueryDTO
     * @return {@link com.sky.result.PageResult}
     * @author L_Misaki
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    /**
     * Desc: 根据ID查询菜品
     * @param id
     * @return {@link DishVO}
     * @author L_Misaki
     */
    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);
}
