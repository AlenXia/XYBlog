package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.entity.ArticleTag;
import com.sangeng.mapper.ArticleTagMapper;
import com.sangeng.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 * @author makejava
 * @since 2022-12-12 20:34:52
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 根据文章Id删除所有Id
     * @param id
     */
    @Override
    public void deleteByArticleId(Integer id) {
        articleTagMapper.deleteByArticleId(id);
    }

    /**
     * 根据文章id查询所有tagId
     * @param id
     * @return
     */
    @Override
    public List<Long> getAllTags(Integer id) {
        return articleTagMapper.getAllTags(id);
    }
}

