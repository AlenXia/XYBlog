package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.entity.Tag;
import com.sangeng.mapper.TagMapper;
import com.sangeng.service.TagService;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}