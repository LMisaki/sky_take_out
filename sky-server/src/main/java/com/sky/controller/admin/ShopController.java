package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@Api(tags = "店铺相关接口")
@RequestMapping("/admin/shop")
public class ShopController {

    public static final String KEY ="SHOP_STATUS";
    @Autowired
    private  RedisTemplate redisTemplate;

    /**
     * Desc: 设置店铺的运营状态
     * @param status
     * @return {@link Result}
     * @author L_Misaki
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态为：{}",status==1? "营业中":"打样中");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }


    /**
     * Desc: 获取商家当前的营业状态
     * @param
     * @return {@link Result<Integer>}
     * @author L_Misaki
     */
    @GetMapping("/{status}")
    @ApiOperation("获取店铺当前的营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("查询当前店铺状态为：{}",status==1?"营业中":"打样中");
        return Result.success(status);
    }
}
