package com.sangeng.domain.dto;

import com.sangeng.domain.entity.Role;
import com.sangeng.domain.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author AlenXia
 * @Date 2023/1/12 9:49
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询用户dto")
public class SelectUserDto {
    private List<Long> roleIds;
    private List<Role> roles;
    private User user;
}
