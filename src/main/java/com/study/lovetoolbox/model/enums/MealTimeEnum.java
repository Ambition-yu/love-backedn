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
public enum MealTimeEnum {

	/**
	 * 早餐
	 */
	BREAKFAST(1,"早餐"),

	/**
	 * 午餐
	 */
	LUNCH(2,"午餐"),

	/**
	 * 晚餐
	 */
	DINNER(3,"晚餐"),
	;

	final Integer code;
	final String name;

	public static MealTimeEnum getValue(Integer code) {
		for (MealTimeEnum e : MealTimeEnum.values()) {
			if (Objects.equals(e.getCode(),code)) {
				return e;
			}
		}
		return null;
	}
}
