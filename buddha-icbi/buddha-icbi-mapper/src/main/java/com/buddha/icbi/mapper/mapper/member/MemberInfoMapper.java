package com.buddha.icbi.mapper.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.icbi.common.param.member.MemberInfoParam;
import com.buddha.icbi.pojo.member.MemberInfo;


  /**
 * 
 * 会员基本信息 Mapper 接口
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {
	
	
	public void updateMemberInfo(@Param("member") MemberInfo member);

	public List<MemberInfo> selectListMember(@Param("param") MemberInfoParam param);

}