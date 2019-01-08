package com.buddha.icbi.common.param.company;

import java.math.BigDecimal;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * //在这里注释类文件的作用等信息
 *
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *佛学禅语：
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *  
 * 
 * @作者   	chuck
 * @时间 	2018年12月11日
 * @版权  	深圳市佛系派互联网科技集团
 */
@Getter
@Setter
public class CompanyInfoParam extends BaseParam{
	
	/**
	 * 会员id
	 */
	private String memberId;
	/**
	 * 是否认证 0-待认证 1-认证通过 2-认证驳回
	 */
	private Integer isCertification;
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
	 * 性别 0-未知 1-男性 -女性
	 */
	private Integer gender;
	/**
	 * 姓氏
	 */
	private String firstName;
	/**
	 * 姓名
	 */
	private String lastName;
	/**
     * 真实头像
     */
	private String realAvatar;
    /**
     * 真实姓名
     */
	private String realName;
    /**
     * 身份证正面
     */
	private String identityFront;
    /**
     * 身份证反面
     */
	private String identityBack;
    /**
     * 个人名片
     */
	private String businessCard;
    /**
     * 个人手机号
     */
	private String mobile;
	/**
	 * 座机号
	 */
	private String landlineNumber;
	/**
     * 公司产品图片
     */
	private String companyProduct;
    /**
     * 公司标签
     */
	private String companyTag;
	/**
	 * 公司简介
	 */
	private String companyProfile;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司营业执照
	 */
	private String companyLicense;
	/**
	 * 公司logo
	 */
	private String companyLogo;
	/**
	 * 公司环境照片
	 */
	private String companyEnvImg;
	/**
	 * 企业网址
	 */
	private String companyWebsite;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 距离
	 */
	private BigDecimal distance;
	/**
	 * 查询类型 1-完善资料 2-未完善资料
	 */
	private Integer queryType;
	/**
	 * 缩放比例
	 */
	private BigDecimal scale;
}
