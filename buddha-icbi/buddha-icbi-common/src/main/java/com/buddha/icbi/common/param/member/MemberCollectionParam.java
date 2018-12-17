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
public class MemberCollectionParam extends BaseParam{

	/**
	 * 收藏会员id
	 */
	private String memberId;
	
	/**
	 * 创建人id 
	 */
	private String createId;
}
