package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServicImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * Desc: 新增菜品和对应的口味
     * @param dishDTO
     * @return
     * @author L_Misaki
     */
    @Transactional  //以内需要同时操作多个数据表，所以需要开启事务
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish= new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        //向菜品表插入1条数据
        dishMapper.insert(dish);

        Long dishId = dish.getId();

        //向口味表插入n条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishId));

            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * Desc: 菜品分页查询
     * @param dishPageQueryDTO
     * @return {@link com.sky.result.PageResult}
     * @author L_Misaki
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * Desc: 菜品批量删除
     * @param ids
     * @return
     * @author L_Misaki
     */
    @Transactional  //多表联调，开启事务
    public void deleteBatch(List<Long> ids) {
        //判断当前菜品是否能删除-----是否在起售中的菜品
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断当前菜品是否能删除----是否被套餐关联
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(setmealIds !=null && !setmealIds.isEmpty()){
            //当前菜品与套餐关联，不能删除
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品表中的数据
        /*for (Long id : ids) {
            dishMapper.deleteByID(id);
            //删除菜品表关联的口味数据
            dishFlavorMapper.deleteByDishId(id);
        }*/

        //根据菜品ID集合批量删除菜品数据
        dishMapper.deleteByIds(ids);

        //删除菜品表关联的口味数据
        dishFlavorMapper.deleteByDishIds(ids);
    }

    /**
     * Desc: 根据ID查询菜品
     * @param id
     * @return {@link DishVO}
     * @author L_Misaki
     */
    public DishVO getByIdWithFlavor(Long id) {
        //根据ID查询菜品数据
        Dish dish = dishMapper.getById(id);

        //根据ID查询口味数据
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);

        //将查询到的数据封装到VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    /**
     * Desc: 根据ID修改菜品基本信息和对应的口味信息 
     * @param dishDTO
     * @return 
     * @author L_Misaki
     */
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        //修改菜品表基本信息
        dishMapper.update(dish);

        //删除原有的口味数据
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        //重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishDTO.getId()));
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }

    }
}
