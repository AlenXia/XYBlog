package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.vo.MenuTreeVo;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.domain.vo.RoleMenuTreeSelectVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-12-10 11:18:50
 */

public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    /**
     * 菜单管理：展示所有菜单 可以查询
     * @param status
     * @param menuName
     * @return
     */
    ResponseResult<MenuVo> listAllMenu(String status, String menuName);

    /**
     * 新增菜单
     * @param menuVo
     * @return
     */
    ResponseResult addMenu(MenuVo menuVo);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    ResponseResult deleteByMenuId(Integer id);

    /**
     * 根据id查询菜单数据
     * @param id
     * @return
     */
    ResponseResult<MenuVo> selectMenuById(Integer id);

    /**
     * 更新菜单
     * @param menuVo
     * @return
     */
    ResponseResult updateMenu(MenuVo menuVo);

    /**
     * 返回菜单树
     * @return
     */
    ResponseResult<MenuTreeVo> selectMenuTree();

    /**
     * 加载对应角色菜单列表
     * @param id
     * @return
     */
    ResponseResult<RoleMenuTreeSelectVo> roleMenuTreeSelect(Long id);
}

