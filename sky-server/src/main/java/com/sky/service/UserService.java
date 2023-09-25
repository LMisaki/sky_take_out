package com.sky.service;


import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Desc: 微信用户登录
     * @param userLoginDTO
     * @return {@link User}
     * @author L_Misaki
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
