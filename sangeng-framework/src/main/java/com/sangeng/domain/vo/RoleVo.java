package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author AlenXia
 * @Date 2022/12/20 17:16
 * @Description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Long roleId;
    private String status;
}
