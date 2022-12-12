package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Tag;

public interface TagMapper extends BaseMapper<Tag> {

    String searchByTagName(String name);
}