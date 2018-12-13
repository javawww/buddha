package com.buddha.icbi.common.bean;

import com.buddha.icbi.pojo.member.MemberInfo;

import lombok.Data;

/**
 * //在这里注释类文件的作用等信息
 *
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *佛学禅语：
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *  
 * 
 * @作者   	chuck
 * @时间 	2018年12月4日
 * @版权  	深圳市佛系派互联网科技集团
 */
@Data
public class LoginUserInfoBean {

	/**
	 * 登录token
	 */
	private String token;

	/**
	 * 客户端类型（安卓或者苹果，参考枚举）
	 */
	private Integer appType;

	/**
	 * 登录来源（web后台、app客户端、微信小程序）
	 */
	private Integer loginSource;
	
	/**
	 * 会员信息对象
	 */
	private MemberInfo memberInfo;
}
