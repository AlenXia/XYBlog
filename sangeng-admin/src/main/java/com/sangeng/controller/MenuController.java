package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.vo.MenuTreeVo;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.domain.vo.RoleMenuTreeSelectVo;
import com.sangeng.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author AlenXia
 * @Date 2022/12/13 9:30
 * @Description
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 展示所有菜单 可以查询
     * @param status
     * @param menuName
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<MenuVo> listAllMenu(String status, String menuName) {
        return menuService.listAllMenu(status, menuName);
    }

    /**
     * 新增菜单
     * @param menuVo
     * @return
     */
    @PostMapping
    public ResponseResult addMenu(@RequestBody MenuVo menuVo) {
        return menuService.addMenu(menuVo);
    }

    /**
     * 根据id查询菜单数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<MenuVo> selectMenuById(@PathVariable("id") Integer id) {
        return menuService.selectMenuById(id);
    }

    /**
     * 更新菜单
     * @param menuVo
     * @return
     */
    @PutMapping
    public ResponseResult updateMenu(@RequestBody MenuVo menuVo) {
        return menuService.updateMenu(menuVo);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteByMenuId(@PathVariable("id") Integer id) {

        return menuService.deleteByMenuId(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult<MenuTreeVo> selectMenuTree() {
        return menuService.selectMenuTree();
    }

    /**
     * 加载对应角色菜单列表
     * @param id
     * @return
     */
    @GetMapping("roleMenuTreeselect/{id}")
    public ResponseResult<RoleMenuTreeSelectVo> roleMenuTreeSelect(@PathVariable("id") Long id) {
        return menuService.roleMenuTreeSelect(id);
    }

}