package com.study.lovetoolbox.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 餐时
 * @author mianhuatang
 */
@Getter
@AllArgsConstructor
public enum NoticeTypeEnum {

	/**
	 * 食谱消息
	 */
	MENU(1,"食谱消息"),

	/**
	 * 申请消息
	 */
	APPLY(2,"申请消息"),
	;

	final Integer code;
	final String name;

	public static NoticeTypeEnum getValue(Integer code) {
		for (NoticeTypeEnum e : NoticeTypeEnum.values()) {
			if (Objects.equals(e.getCode(),code)) {
				return e;
			}
		}
		return null;
	}
}
