package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleVo;
import com.sangeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author AlenXia
 * @Date 2022/12/14 22:43
 * @Description 对角色信息的操作
 */
@RestController
@RequestMapping("system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     * @param pageNum
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> listAllArticle(Integer pageNum, Integer pageSize, String roleName, String status) {
        return roleService.listAllArticle(pageNum, pageSize, roleName, status);
    }


    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleVo roleVo) {
        return roleService.changeStatus(roleVo);
    }
}
