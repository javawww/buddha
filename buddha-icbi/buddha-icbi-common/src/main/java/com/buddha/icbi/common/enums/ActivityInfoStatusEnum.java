package com.buddha.icbi.common.enums;

import lombok.Getter;

@Getter
public enum ActivityInfoStatusEnum {
	/**
	 *  1-报名中 
	 */
	ENROLLMENT(1, "报名中"),
	
	/**
	 * 2-进行中 
	 */
	PROCESSING(2, "进行中"),

	/**
	 * 3-已结束
	 */
	ALREADY_OVER(3, "已结束"),
	
	;

	/**
	 * 状态值
	 */
	private final Integer value;

	/**
	 * 说明
	 */
	private final String desc;

	private ActivityInfoStatusEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value
	 * @return
	 */
	public static ActivityInfoStatusEnum getStatusEnum(Integer value) {
		if (value == null) {
			return null;
		}
		for (ActivityInfoStatusEnum enums : ActivityInfoStatusEnum.values()) {
			if (value == enums.getValue()) {
				return enums;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
