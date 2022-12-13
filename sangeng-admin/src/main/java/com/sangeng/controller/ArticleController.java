package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.AddArticleDto;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     * @param article
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    /**
     * 展示所有文章 分页
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> listAllArticle(Integer pageNum, Integer pageSize, String title, String summary){
        return articleService.listAllArticle(pageNum,pageSize,title,summary);
    }

    /**
     * 根据id查询文章数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<AddArticleDto> selectArticleById(@PathVariable("id")Integer id) {
        return articleService.selectArticleById(id);
    }

    /**
     * 修改文章
     * @param articleDto
     * @return
     */
    @PutMapping
    public ResponseResult updateArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return articleService.deleteById(id);
    }
}