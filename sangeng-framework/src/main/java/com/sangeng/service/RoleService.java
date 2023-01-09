package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddRoleDto;
import com.sangeng.domain.dto.SelectRoleDto;
import com.sangeng.domain.dto.UpdateRoleDto;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleMenuTreeSelectVo;
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
    ResponseResult<PageVo> listAllRole(Integer pageNum, Integer pageSize, String roleName, String status);


    /**
     * 改变角色状态
     * @param roleVo
     * @return
     */
    ResponseResult changeStatus(RoleVo roleVo);

    /**
     * 添加角色
     * @param addRoleDto
     * @return
     */
    ResponseResult addRole(AddRoleDto addRoleDto);

    /**
     * 角色信息显示
     * @param id
     * @return
     */
    ResponseResult<SelectRoleDto> selectById(Integer id);

    /**
     * 加载对应角色菜单列表树
     * @param id
     * @return
     */
    ResponseResult<RoleMenuTreeSelectVo> roleMenuTreeSelect(Long id);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    ResponseResult updateRole(UpdateRoleDto updateRoleDto);
}

