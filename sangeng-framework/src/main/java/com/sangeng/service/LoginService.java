package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

public interface LoginService {

    /**
     * 后台登陆
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 后台退出登陆
     * @return
     */
    ResponseResult logout();
}
