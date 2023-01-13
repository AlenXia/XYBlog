package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Link;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author AlenXia
 * @Date 2022/10/26 20:03
 * @Description
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }

    @GetMapping("/list")
    public ResponseResult<PageVo> listAllLink(Integer pageNum, Integer pageSize, String name, String staus) {
        return linkService.listAllLink(pageNum, pageSize, name, staus);
    }

    @GetMapping("/{id}")
    public ResponseResult<Link> selectLink(@PathVariable("id") Long id){
        return linkService.selectLink(id);
    }

    @PutMapping
    public ResponseResult updateLink(@RequestBody Link link){
        return linkService.updateLink(link);
    }

    @PostMapping
    public ResponseResult addLink(@RequestBody Link link){
        return linkService.addLink(link);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable("id") Long id) {
        return linkService.deleteLink(id);
    }
}
