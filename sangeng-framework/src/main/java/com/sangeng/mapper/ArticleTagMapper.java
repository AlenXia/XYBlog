package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.ArticleTag;

import java.util.List;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-12 20:34:50
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    void deleteByArticleId(Integer id);

    /**
     * 根据文章id 获取所有标签id
     * @param id
     * @return
     */
    List<Long> getAllTags(Integer id);
}

