package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddCategoryDto;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.CategoryVo;
import com.sangeng.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-10-25 16:11:22
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    /**
     * 展示所有分类
     * @return
     */
    List<CategoryVo> listAllCategory();

    /**
     * 分页查询分类列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param status
     * @return
     */
    ResponseResult<PageVo> listCategory(Integer pageNum, Integer pageSize, String name, String status);

    /**
     * 添加菜单
     * @param addCategoryDto
     * @return
     */
    ResponseResult addCategory(AddCategoryDto addCategoryDto);
}

