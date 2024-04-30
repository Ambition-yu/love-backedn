package com.study.lovetoolbox.model.dto.common;

import lombok.Data;

@Data
public class Query {

    private long current;
    private long size;

    {
        current = 1;
        size = 5;
    }
}
