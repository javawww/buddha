package com.buddha.icbi.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class MemberLocationDto implements Serializable{

	private String address;// 地址
	private Integer height;// 图片高度
	private Integer width;// 图片宽度
	private String iconPath;// 图片地址
	private Integer id;// 索引id
	private String memberId;// 会员id
	private String companyId;// 公司id 
	private BigDecimal latitude;// 位置
	private BigDecimal longitude;// 位置
	private String name;// 姓名
	private BigDecimal distance;// 距离
	private String realName;// 真实姓名
	private String realAvatar;// 真实头像
	private String mobile;// 手机号
	
}
