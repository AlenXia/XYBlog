package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddUserDto;
import com.sangeng.domain.dto.DeleteUserDto;
import com.sangeng.domain.dto.SelectUserDto;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult<PageVo> listAllUser(
            Integer pageNum,
            Integer pageSize,
            String userName,
            String phonenumber,
            String status) {
        return userService.listAllUser(pageNum,
                pageSize,
                userName,
                phonenumber,
                status);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult<SelectUserDto> findUser(@PathVariable("id") Long id) {
        return userService.findUser(id);
    }

    @PutMapping
    public ResponseResult deleteUser(@RequestBody DeleteUserDto deleteUserDto) {
        return userService.deleteUser(deleteUserDto.getId());
    }
}