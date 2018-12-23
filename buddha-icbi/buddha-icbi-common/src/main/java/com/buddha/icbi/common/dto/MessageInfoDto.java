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
public class MessageInfoDto implements Serializable{

	private String realName; // 姓名
	private String realAvatar;// 头像
	private Integer counts;// 数量
	private String newMsg;// 最新消息
	private String time;// 时间
	private String fromId;// 消息来源
	private String toId;// 消息去向
}
