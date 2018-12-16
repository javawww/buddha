package com.buddha.icbi.common.enums;

import lombok.Getter;

@Getter
public enum MemberOrganizationTypeEnum {
	/**
	 * 商会
	 */
	COMMERCE(1, "商会"),
	
	/**
	 * 协会
	 */
	SOCIETY(2, "协会"),

	/**
	 * 母公司
	 */
	PARENT_COMPANY(3, "母公司"),
	
	;

	/**
	 * 状态值
	 */
	private final Integer value;

	/**
	 * 说明
	 */
	private final String desc;

	private MemberOrganizationTypeEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value
	 * @return
	 */
	public static MemberOrganizationTypeEnum getTypeEnum(Integer value) {
		if (value == null) {
			return null;
		}
		for (MemberOrganizationTypeEnum enums : MemberOrganizationTypeEnum.values()) {
			if (value == enums.getValue()) {
				return enums;
			}
		}
		return null;
	}
}
