package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AlenXia
 * @Date 2023/1/12 14:50
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加菜单dto")
public class AddCategoryDto {
    String name;
    String description;
    String status;
}
