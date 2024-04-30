package com.study.lovetoolbox.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.lovetoolbox.model.dto.common.Query;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    public static <T> IPage<T> getPage(Query query) {
        return (Page<T>) new Page(query.getCurrent(), query.getSize());
    }
}
