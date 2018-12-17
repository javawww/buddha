package com.buddha.icbi.common.enums;

import lombok.Getter;

/**
 * 审核状态枚举
 *
 * #############################################################################
 * CopyRight (C) 2018 ShenZhen ZhiZaoJianZhu Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市智造建筑信息科技有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @Author      wangtao
 * @Date        2018-7-26
 * @Copyright   深圳市智造建筑信息科技有限公司(www.icbi.xin)
 */
@Getter
public enum IsAdminEnum {
	/**
	 * 普通会员
	 */
	ORDINARY_MEMBER(1, "普通会员"),
	
	/**
	 * 审核通过
	 */
	SUPER_ADMIN(2, "超级管理员"),
 
	;

	/**
	 * 状态值
	 */
	private final Integer value;

	/**
	 * 说明
	 */
	private final String desc;

	private IsAdminEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 通过状态值获取状态枚举
	 * 
	 * @param value
	 * @return
	 */
	public static IsAdminEnum getAdminEnum(Integer value) {
		if (value == null) {
			return null;
		}
		for (IsAdminEnum enums : IsAdminEnum.values()) {
			if (value == enums.getValue()) {
				return enums;
			}
		}
		return null;
	}
}
