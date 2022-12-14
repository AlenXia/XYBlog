package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.TagListDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.TagVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.TagMapper;
import com.sangeng.service.TagService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(TagListDto tagListDto) {
        // 判断是否为空
        if (!StringUtils.hasText(tagListDto.getName())) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        if (!StringUtils.hasText(tagListDto.getRemark())) {
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NOT_NULL);
        }

        // 判断是否重复
        if (StringUtils.hasText(getBaseMapper().searchByTagName(tagListDto.getName()))) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_EXIST);
        }

        Tag tag = new Tag();
        tag.setName(tagListDto.getName());
        tag.setRemark(tagListDto.getRemark());
        tag.setDelFlag(0);
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Integer id) {
        Tag tag = getById(id);
        if (tag == null || tag.getDelFlag().equals(1)) {
            throw new SystemException(AppHttpCodeEnum.TAG_NOT_EXIST);
        }
        getBaseMapper().deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<TagVo> getTag(Integer id) {
        Tag tag = getById(id);
        TagVo tagVo = new TagVo();
        tagVo.setId(tag.getId());
        tagVo.setName(tag.getName());
        tagVo.setRemark(tag.getRemark());
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTag(TagVo tagVo) {
        Tag tag = getById(tagVo.getId());
        tag.setName(tagVo.getName());
        tag.setRemark(tagVo.getRemark());
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}