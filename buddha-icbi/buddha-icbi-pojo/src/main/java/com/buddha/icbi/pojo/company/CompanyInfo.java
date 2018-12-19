package com.buddha.icbi.pojo.company;

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
 * 公司信息-数据库实体对象
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
 * @时间 2018-12-10
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@TableName("company_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyInfo extends PojoModel<CompanyInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 会员id
	 */
	@TableField("member_id")
	private String memberId;
	/**
	 * 纬度，范围为 -90~90，负数表示南纬
	 */
	private BigDecimal latitude;
	/**
	 * 经度，范围为 -180~180，负数表示西经
	 */
	private BigDecimal longitude;
	/**
	 * 位置名称
	 */
	private String name;
	/**
	 * 具体办公地点
	 */
	private String address;
	/**
	 * 楼层
	 */
	private String floor;
	/**
     * 真实头像
     */
	@TableField("real_avatar")
	private String realAvatar;
    /**
     * 真实姓名
     */
	@TableField("real_name")
	private String realName;
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
     * 个人名片
     */
	@TableField("business_card")
	private String businessCard;
    /**
     * 个人手机号
     */
	private String mobile;
	/**
	 * 座机号
	 */
	@TableField("landline_number")
	private String landlineNumber;
	/**
     * 公司产品图片
     */
	@TableField("company_product")
	private String companyProduct;
    /**
     * 公司标签
     */
	@TableField("company_tag")
	private String companyTag;
	/**
	 * 公司简介
	 */
	@TableField("company_profile")
	private String companyProfile;
	/**
	 * 公司名称
	 */
	@TableField("company_name")
	private String companyName;
	/**
	 * 公司营业执照
	 */
	@TableField("company_license")
	private String companyLicense;
	/**
	 * 公司logo
	 */
	@TableField("company_logo")
	private String companyLogo;
	/**
	 * 公司环境照片
	 */
	@TableField("company_env_img")
	private String companyEnvImg;
	/**
	 * 企业网址
	 */
	@TableField("company_website")
	private String companyWebsite;
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
	
	// --------额外属性（entity对应表结构之外的属性） start------------------------------
	/**
	 * 距离 单位公里
	 */
	@TableField(exist = false)
	private BigDecimal distance;
	
	@TableField(exist = false)
	private String[] realAvatarArr;
	@TableField(exist = false)
	private String[] identityFrontArr;
	@TableField(exist = false)
	private String[] identityBackArr;
	@TableField(exist = false)
	private String[] companyProductArr;
	@TableField(exist = false)
	private String[] companyLicenseArr;
	@TableField(exist = false)
	private String[] companyLogoArr;
	@TableField(exist = false)
	private String[] companyEnvImgArr;
	// --------额外属性（entity对应表结构之外的属性） end--------------------------------

}
