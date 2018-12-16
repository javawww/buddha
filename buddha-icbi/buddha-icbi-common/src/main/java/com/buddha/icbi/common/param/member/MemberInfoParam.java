package com.buddha.icbi.common.param.member;

import java.math.BigDecimal;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * //在这里注释类文件的作用等信息
 *
 * <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 佛学禅语： <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 
 * 
 * @作者 chuck
 * @时间 2018年12月4日
 * @版权 深圳市佛系派互联网科技集团
 */
@Getter
@Setter
public class MemberInfoParam extends BaseParam {

	/**
	 * 客户端类型（安卓或者苹果，参考枚举）
	 */
	private Integer appType;

	/**
	 * 登录来源（web后台、app客户端）
	 */
	private Integer loginSource;

	/**
	 * 父id
	 */
	private String parentId;

	/**
	 * 公众平台unionid
	 */
	private String unionId;

	/**
	 * 微信access token
	 */
	private String accessToken;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 状态：1-正常 2-禁用
	 */
	private Integer status;
	/**
	 * 等级：0-7颗星 默认0颗 其他根据认证审核
	 */
	private Integer level;
	/**
	 * 是否管理员 1-非管理员 2-平台管理员
	 */
	private Integer isAdmin;

	/**
	 * 是否认证 1-待认证 2-认证通过 3-认证驳回
	 */
	private Integer isCertification;
	/**
	 * 是否隐私保护 1-公开 2-隐私保护
	 */
	private Integer isPrivacy;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 地区
	 */
	private String country;
	/**
	 * 真实头像
	 */
	private String realAvatar;
	/**
	 * 性别 0-未知 1-男性 2-女性
	 */
	private Integer gender;
	/**
	 * 纬度，范围为 -90~90，负数表示南纬
	 */
	private BigDecimal latitude;
	/**
	 * 经度，范围为 -180~180，负数表示西经
	 */
	private BigDecimal longitude;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 身份证正面
	 */
	private String identityFront;
	/**
	 * 身份证反面
	 */
	private String identityBack;
	/**
	 * 名片
	 */
	private String businessCard;

	/**
	 * 客户端ip地址
	 */
	private String clientIp;
}
