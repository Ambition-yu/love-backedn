package com.study.lovetoolbox.model.dto.menu;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MenuSaveDTO {

    /**
     * 日期
     */
    private Date menuDate;

    /**
     * 餐时
     */
    private Integer mealTime;
}
