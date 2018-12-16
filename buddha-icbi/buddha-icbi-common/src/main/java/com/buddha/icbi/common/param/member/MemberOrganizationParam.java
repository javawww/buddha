package com.buddha.icbi.common.param.member;

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
 * @时间 	2018年12月4日
 * @版权  	深圳市佛系派互联网科技集团
 */
@Getter
@Setter
public class MemberOrganizationParam extends BaseParam{
	
	/**
	 * 会员id
	 */
	private String memberId;
	/**
	 * 审核人id
	 */
	private String auditId;
	 /**
     * 名称 某某商会 某某协会 某某母公司
     */
	private String name;
    /**
     * 组织类型 1-商会 2-协会 3-母公司
     */
	private Integer type;
    /**
     * 状态 0-待审核 1-审核通过 2-审核拒绝
     */
	private Integer status;
	/**
	 * 拒绝原因
	 */
	private String refuse;
}
