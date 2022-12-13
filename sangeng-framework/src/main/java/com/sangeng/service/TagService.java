package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    /**
     * 展示标签 分页
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    /**
     * 添加标签
     * @param tagListDto
     * @return
     */
    ResponseResult addTag(TagListDto tagListDto);

    /**
     * 删除标签
     * @param id
     * @return
     */
    ResponseResult deleteTag(Integer id);

    /**
     * 根据id获取标签
     * @param id
     * @return
     */
    ResponseResult<TagVo> getTag(Integer id);

    /**
     * 修改标签
     * @param tagVo
     * @return
     */
    ResponseResult updateTag(TagVo tagVo);

    /**
     * 展示所有标签
     * @return
     */
    List<TagVo> listAllTag();
}
