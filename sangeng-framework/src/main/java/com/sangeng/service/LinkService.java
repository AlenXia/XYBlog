package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Link;
import com.sangeng.domain.vo.PageVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-10-26 20:06:31
 */
public interface LinkService extends IService<Link> {

    public ResponseResult getAllLink();

    /**
     * 分页展示所有列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @param staus
     * @return
     */
    ResponseResult<PageVo> listAllLink(Integer pageNum, Integer pageSize, String name, String staus);

    /**
     * 友链信息回显
     * @param id
     * @return
     */
    ResponseResult<Link> selectLink(Long id);

    /**
     * 更新友链
     * @param link
     * @return
     */
    ResponseResult updateLink(Link link);
}

