package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddRoleDto;
import com.sangeng.domain.dto.SelectRoleDto;
import com.sangeng.domain.dto.UpdateRoleDto;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.entity.RoleMenu;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleVo;
import com.sangeng.mapper.RoleMapper;
import com.sangeng.service.RoleMenuService;
import com.sangeng.service.RoleService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 * @author makejava
 * @since 2022-12-10 11:25:16
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断是否为管理员 如果是返回集合中只需要有admin
        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
        }
        // 否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult<PageVo> listAllRole(Integer pageNum, Integer pageSize, String roleName, String status) {
        // 添加模糊查询条件
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(roleName)) {
            queryWrapper.like(Role::getRoleName, roleName);
        }
        if (StringUtils.hasText(status)) {
            queryWrapper.like(Role::getStatus, status);
        }
        //分页查询
        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleVo roleVo) {
        // 根据id查询出角色
        Role role = getBaseMapper().selectById(roleVo.getRoleId());
        // 修改角色状态信息
        if (role.getStatus().equals("1")) {
            role.setStatus("0");
        } else {
            role.setStatus("1");
        }
        // 更新角色信息
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addRole(AddRoleDto addRoleDto) {
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);
        // 保存角色和权限的关系
        List<RoleMenu> roleMenus = addRoleDto.getMenuIds().stream()
                .map(menuId -> {
                    return new RoleMenu(role.getId(), menuId.longValue());
                })
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<SelectRoleDto> selectById(Integer id) {
        Role role = getBaseMapper().selectById(id);
        SelectRoleDto selectRoleDto = BeanCopyUtils.copyBean(role, SelectRoleDto.class);
        return ResponseResult.okResult(selectRoleDto);
    }

    @Override
    public ResponseResult updateRole(UpdateRoleDto updateRoleDto) {

        // 判断角色是否存在
        Role select = getBaseMapper().selectById(updateRoleDto.getId());
        if (select == null) {
            throw new RuntimeException("被修改的角色不存在");
        }

        // 删除role_menu表中的关系
        LambdaQueryWrapper<RoleMenu> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, updateRoleDto.getId());
        roleMenuService.getBaseMapper().delete(queryWrapper);

        // 添加role_menu表中的关系
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(updateRoleDto.getId());
        for (int i = 0; i < updateRoleDto.getMenuIds().toArray().length; i++) {
            roleMenu.setMenuId(updateRoleDto.getMenuIds().get(i));
            roleMenuService.getBaseMapper().insert(roleMenu);
        }

        // 更新角色信息
        Role role = BeanCopyUtils.copyBean(updateRoleDto, Role.class);
        getBaseMapper().updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(Long id) {
        // 查找角色是否存在
        Role role = getBaseMapper().selectById(id);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 下面代码应该是不需要删的，因为Role并不是物理删除
        // 删除role_menu表中的关系
        // LambdaQueryWrapper<RoleMenu> queryWrapper=new LambdaQueryWrapper<>();
        // queryWrapper.eq(RoleMenu::getRoleId, id);
        // roleMenuService.getBaseMapper().delete(queryWrapper);

        // 删除角色
        getBaseMapper().deleteById(id);

        return ResponseResult.okResult();
    }
}

