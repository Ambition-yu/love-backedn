package com.study.lovetoolbox.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MenuListVO {

    /**
     * 日期
     */
    private Long id;
    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date menuDate;

    /**
     * 餐时
     */
    private Integer mealTime;
}
