package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from sky_take_out.dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * Desc: 新增菜品数据 
     * @param dish
     * @return 
     * @author L_Misaki
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * Desc: 菜品分页查询
     * @param dishPageQueryDTO
     * @return {@link Page< DishVO>}
     * @author L_Misaki
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from sky_take_out.dish where id = #{id}")
    Dish getById(Long id);

    @Delete("delete from sky_take_out.dish where id = #{id}")
    void deleteByID(Long id);

   /**
    * Desc: 根据菜品Id集合删除菜品
    * @param ids
    * @return
    * @author L_Misaki
    */
    void deleteByIds(List<Long> ids);

   /**
    * Desc: 根据ID动态修改数据 
    * @param dish
    * @return 
    * @author L_Misaki
    */
    void update(Dish dish);
}
