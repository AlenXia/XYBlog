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

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

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

    @PutMapping
    public ResponseResult updateArticle(@RequestBody AddArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {

        return articleService.deleteById(id);
    }
}