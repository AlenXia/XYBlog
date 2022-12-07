package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.sangeng.service.CommentService;

/**
 * 评论表(SgComment)表服务实现类
 *
 * @author makejava
 * @since 2022-12-07 17:25:58
 */
@Service("sgCommentService")
public class CommentServiceImpl extends ServiceImpl<SgCommentMapper, SgComment> implements CommentService {

}

