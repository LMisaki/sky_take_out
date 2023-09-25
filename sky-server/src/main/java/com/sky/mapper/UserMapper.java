package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    /**
     * Desc: 根据openid查询用户
     * @param openid
     * @return {@link User}
     * @author L_Misaki
     */
    @Select("select * from sky_take_out.user where openid =#{openid}")
    User getByOpenid(String openid);

    /**
     * Desc: 插入用户数据
     * @param user
     * @return
     * @author L_Misaki
     */
    void insert(User user);

}
