package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AlenXia
 * @Date 2022/12/21 17:06
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询角色dto")
public class SelectRoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    String remark;
}
