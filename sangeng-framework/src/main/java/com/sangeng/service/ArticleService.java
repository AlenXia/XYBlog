package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddArticleDto;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.PageVo;

/**
 * @Author AlenXia
 * @Date 2022/10/25 9:28
 * @Description
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    /**
     * 查询文章列表
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    ResponseResult<PageVo> listAllArticle(Integer pageNum, Integer pageSize, String title, String summary);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    ResponseResult<AddArticleDto> selectArticleById(Integer id);

    /**
     * 更新文章
     * @param articleDto
     * @return
     */
    ResponseResult updateArticle(AddArticleDto articleDto);

    /**
     * 根据文章id删除文章
     * @param id
     * @return
     */
    ResponseResult deleteById(Integer id);
}
