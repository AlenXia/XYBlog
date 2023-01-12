package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AlenXia
 * @Date 2023/1/12 15:08
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询菜单dto")
public class SelectCategoryDto {
    //描述
    private String description;
    private Long id;
    private String name;
    private String status;
}
