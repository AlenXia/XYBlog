package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

/**
 * @Author AlenXia
 * @Date 2022/10/29 17:08
 * @Description
 */
public interface BlogLoginService {
    ResponseResult login(User user);
}
