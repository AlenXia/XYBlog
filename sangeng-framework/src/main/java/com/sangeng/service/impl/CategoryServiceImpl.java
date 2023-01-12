package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddCategoryDto;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.CategoryVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 * @author makejava
 * @since 2022-10-25 16:11:23
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        // 先查询文章表
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        // 获取文章的分类id 要去重
        Set<Long> categoryId = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 再查询分类表 封装数据
        List<Category> categories = listByIds(categoryId);

        // 选择正常状态
        categories.stream()
                .filter(category -> category.getStatus().equals(SystemConstants.STATUS_NORMAL))
                .collect(Collectors.toList());

        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public ResponseResult<PageVo> listCategory(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Category::getName, "name");
        }
        if (StringUtils.hasText(status)) {
            queryWrapper.like(Category::getStatus, status);
        }

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCategory(AddCategoryDto addCategoryDto) {
        // 判断菜单是否已经存在
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName, addCategoryDto.getName());
        if (getBaseMapper().selectOne(queryWrapper) != null) {
            throw new SystemException(AppHttpCodeEnum.CATEGORY_EXIST);
        }

        Category category = BeanCopyUtils.copyBean(addCategoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }
}

