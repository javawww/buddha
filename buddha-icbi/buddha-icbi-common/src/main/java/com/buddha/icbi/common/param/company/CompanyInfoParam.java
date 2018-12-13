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
	 * 纬度，范围为 -90~90，负数表示南纬
	 */
	private BigDecimal latitude;
	/**
	 * 经度，范围为 -180~180，负数表示西经
	 */
	private BigDecimal longitude;
	/**
	 * 具体办公地点
	 */
	private String address;
	/**
	 * 座机号
	 */
	private String landlineNumber;
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
	 * 企业网址
	 */
	private String companyWebsite;

}
