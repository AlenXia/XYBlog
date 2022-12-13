package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.service.MenuService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 * @author makejava
 * @since 2022-12-10 11:18:51
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员，则返回所有权限
        if (id == 1L) {
            LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            lambdaQueryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(lambdaQueryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        // 否则返回其所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        // 判断是否为管理员
        if (SecurityUtils.isAdmin()) {
            // 如果是 返回所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 否则 当前用户所具有的的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus, 0L);
        return menuTree;
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

    @Override
    public ResponseResult<MenuVo> listAllMenu(String status, String menuName) {
        // 判断查询条件时都存在
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(status)) {
            queryWrapper.like(Menu::getStatus, status);
        }
        if (StringUtils.hasText(menuName)) {
            queryWrapper.like(Menu::getMenuName, menuName);
        }
        // 进行排序
        queryWrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum);

        List<Menu> menuList = list(queryWrapper);

        List<MenuVo> menuVoList = BeanCopyUtils.copyBeanList(menuList, MenuVo.class);

        return ResponseResult.okResult(menuVoList);
    }


    @Override
    public ResponseResult addMenu(MenuVo menuVo) {
        // 判断传入值是否为空
        if (menuVo == null) {
            throw new RuntimeException("保存文章为空");
        }
        Menu menu = BeanCopyUtils.copyBean(menuVo, Menu.class);
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteByMenuId(Integer id) {
        // 判断是否存在这个MenuId
        if (id == null) {
            throw new RuntimeException("传入id为空");
        }
        // 根据MenuId逻辑删除
        getBaseMapper().deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<MenuVo> selectMenuById(Integer id) {
        Menu menu = getBaseMapper().selectById(id);
        if (menu == null||menu.getDelFlag().equals(1)) {
            throw new RuntimeException("菜单不存在或已被删除");
        }
        MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        return ResponseResult.okResult(menuVo);
    }

    @Override
    public ResponseResult updateMenu(MenuVo menuVo) {
        Menu menu = BeanCopyUtils.copyBean(menuVo, Menu.class);
        updateById(menu);
        return ResponseResult.okResult();
    }
}

