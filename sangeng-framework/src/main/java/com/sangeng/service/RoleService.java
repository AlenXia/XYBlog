package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleVo;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-12-10 11:25:16
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    /**
     * 展示角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    ResponseResult<PageVo> listAllArticle(Integer pageNum, Integer pageSize, String roleName, String status);


    ResponseResult changeStatus(RoleVo roleVo);
}

