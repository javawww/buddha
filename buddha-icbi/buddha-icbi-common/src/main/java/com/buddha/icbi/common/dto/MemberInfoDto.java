package com.buddha.icbi.common.dto;

import java.io.Serializable;
import java.util.List;

import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberInfo;
import com.buddha.icbi.pojo.member.MemberOrganization;

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
 * @时间 	2018年12月8日
 * @版权  	深圳市佛系派互联网科技集团
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class MemberInfoDto implements Serializable{

	/**
	 * 会员信息
	 */
	private MemberInfo memberInfo;
	
	/**
	 * 会员产品图片
	 */
	private List<String> proImageArr;
	
	/**
	 * 会员所属行业标签
	 */
	private List<String> proTagArr;
	/**
	 * 公司信息
	 */
	private CompanyInfo companyInfo;
	
	/**
	 * 同上展示
	 */
	private String productTags;
	
	/**
	 * 会员所属组织
	 */
	private List<MemberOrganization> organizationArr;
	
}
