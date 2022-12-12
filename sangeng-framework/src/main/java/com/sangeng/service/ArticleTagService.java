package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.entity.ArticleTag;

import java.util.List;


/**
 * 文章标签关联表(ArticleTag)表服务接口
 *
 * @author makejava
 * @since 2022-12-12 20:34:52
 */
public interface ArticleTagService extends IService<ArticleTag> {

    void deleteByArticleId(Integer id);

    List<Long> getAllTags(Integer id);
}

