package com.sangeng.domain.vo;

import com.sangeng.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author AlenXia
 * @Date 2022/12/11 22:51
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}

