package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author AlenXia
 * @Date 2023/1/9 17:56
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改角色dto")
public class UpdateRoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private List<Integer> menuIds;
    String remark;
}

