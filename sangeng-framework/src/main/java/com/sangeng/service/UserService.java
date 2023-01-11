package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddUserDto;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.PageVo;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-02-09 00:28:29
 */
public interface UserService extends IService<User> {

    /**
     * 展示用户信息
     * @return
     */
    ResponseResult userInfo();

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ResponseResult updateUserInfo(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    ResponseResult register(User user);

    /**
     * 展示用户列表
     * @param pageNum
     * @param pageSize
     * @param userName
     * @param phonenumber
     * @param status
     * @return
     */
    ResponseResult<PageVo> listAllUser(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    /**
     * 添加用户
     * @param addUserDto
     * @return
     */
    ResponseResult addUser(AddUserDto addUserDto);
}

