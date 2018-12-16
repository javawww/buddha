package com.buddha.icbi.pojo.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.common.bean.mybatis.PojoModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 会员基本信息-数据库实体对象
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网科技设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@TableName("member_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberInfo extends PojoModel<MemberInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 父id
	 */
	@TableField("parent_id")
	private String parentId;
	/**
	 * 公众平台union id
	 */
	@TableField("union_id")
	private String unionId;
	/**
	 * 微信openid
	 */
	@TableField("open_id")
	private String openId;
	/**
	 * 微信access token
	 */
	@TableField("access_token")
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
	@TableField("is_admin")
	private Integer isAdmin;

	/**
	 * 是否认证 1-待认证 2-认证通过 3-认证驳回
	 */
	@TableField("is_certification")
	private Integer isCertification;
	/**
	 * 是否隐私保护 1-公开 2-隐私保护
	 */
	@TableField("is_privacy")
	private Integer isPrivacy;
	/**
	 * 昵称
	 */
	@TableField("nick_name")
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
	@TableField("real_avatar")
	private String realAvatar;
	/**
	 * 性别 0-未知 1-男性 -女性
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
	@TableField("real_name")
	private String realName;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 身份证正面
	 */
	@TableField("identity_front")
	private String identityFront;
	/**
	 * 身份证反面
	 */
	@TableField("identity_back")
	private String identityBack;
	/**
	 * 名片
	 */
	@TableField("business_card")
	private String businessCard;
	/**
	 * 最后登录ip
	 */
	@TableField("last_login_ip")
	private String lastLoginIp;
	/**
	 * 最后登录时间
	 */
	@TableField("last_login_time")
	private Date lastLoginTime;
	/**
	 * 是否删除 1-正常 2-删除
	 */
	@TableField("is_del")
	private Integer isDel;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
