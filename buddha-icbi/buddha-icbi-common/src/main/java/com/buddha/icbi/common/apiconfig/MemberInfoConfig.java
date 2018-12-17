package com.buddha.icbi.common.apiconfig;

public interface MemberInfoConfig {

	/**
	 * 获取会员openid
	 */
	public static final String MEMBER_INFO_GET_OPENID = "member-info/get-openid";
	
	/**
	 * 登录
	 */
	public static final String MEMBER_INFO_LOGIN = "member-info/login";
	
	/**
	 * 初始化注册
	 */
	public static final String MEMBER_INFO_REGISTER = "member-info/register";
	
	/**
	 * 详情
	 */
	public static final String MEMBER_INFO_DETAIL = "member-info/detail";
	
	/**
	 * 更新
	 */
	public static final String MEMBER_INFO_UPDATE = "member-info/update";
	
	/**
	 * 会员列表
	 */
	public static final String MEMBER_INFO_LIST_MEMBER = "member-info/list-member";
	
	/**
	 * 待审核列表
	 */
	public static final String MEMBER_INFO_WAIT_AUDIT = "member-info/wait-audit";
	/**
	 * 审核通过
	 */
	public static final String MEMBER_INFO_PASS = "member-info/pass";
	/**
	 * 审核拒绝
	 */
	public static final String MEMBER_INFO_REJECT = "member-info/reject";
	
	
	
}
