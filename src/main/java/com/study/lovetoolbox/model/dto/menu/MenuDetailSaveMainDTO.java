package com.study.lovetoolbox.model.dto.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuDetailSaveMainDTO {

    /**
     * menu id
     */
    private Long menuId;

    private List<MenuDetailSaveListDTO> list;

}
