package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.TagVo;
import com.sangeng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 展示标签 分页
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    /**
     * 添加标签
     * @param tagListDto
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto) {
        return tagService.addTag(tagListDto);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Integer id) {
        return tagService.deleteTag(id);
    }

    /**
     * 根据id获取标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<TagVo> getTag(@PathVariable("id") Integer id) {
        return tagService.getTag(id);
    }

    /**
     * 修改标签
     * @param tagVo
     * @return
     */
    @PutMapping
    public ResponseResult updateTag(@RequestBody TagVo tagVo) {
        return tagService.updateTag(tagVo);
    }

    /**
     * 展示所有标签
     * @return
     */
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}