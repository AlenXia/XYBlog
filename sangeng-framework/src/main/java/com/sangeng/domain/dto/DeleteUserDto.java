package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author AlenXia
 * @Date 2023/1/12 13:33
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "删除角色dto")
public class DeleteUserDto {
    String email;
    Long id;
    String nickName;
    String sex;
    String status;
    String userName;
    List<Long> roleIds;
}
