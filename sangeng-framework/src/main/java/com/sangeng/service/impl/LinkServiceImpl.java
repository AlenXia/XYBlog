package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Link;
import com.sangeng.domain.vo.LinkVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.LinkMapper;
import com.sangeng.service.LinkService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 * @author makejava
 * @since 2022-10-26 20:06:33
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的友链
        LambdaQueryWrapper<Link> QueryWrapper = new LambdaQueryWrapper();
        QueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(QueryWrapper);
        // 封装VO
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult<PageVo> listAllLink(Integer pageNum, Integer pageSize, String name, String status) {
        // 模糊查询
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            queryWrapper.like(Link::getName, "name");
        }
        if (StringUtils.hasText(status)) {
            queryWrapper.like(Link::getStatus, status);
        }

        Page<Link> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<Link> selectLink(Long id) {
        Link link = getBaseMapper().selectById(id);
        return ResponseResult.okResult(link);
    }

    @Override
    public ResponseResult updateLink(Link link) {
        Link result = getBaseMapper().selectById(link.getId());
        if (result == null) {
            throw new SystemException(AppHttpCodeEnum.LINK_NOT_EXIST);
        }

        getBaseMapper().updateById(link);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addLink(Link link) {
        // 判断名称是否重合
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getName, link.getName());
        Link selectByName = getBaseMapper().selectOne(queryWrapper);

        if (selectByName != null) {
            throw new SystemException(AppHttpCodeEnum.LINK_NAME_EXIST);
        }
        save(link);

        return ResponseResult.okResult();
    }
}

