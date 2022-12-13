package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.vo.MenuVo;
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
    public ResponseResult addMenu(@RequestBody MenuVo menuVo){
        return menuService.addMenu(menuVo);
    }

    /**
     * 根据id查询菜单数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<MenuVo> selectMenuById(@PathVariable("id")Integer id) {
        return menuService.selectMenuById(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody MenuVo menuVo) {
        return menuService.updateMenu(menuVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteByMenuId(@PathVariable("id") Integer id) {

        return menuService.deleteByMenuId(id);
    }


}
