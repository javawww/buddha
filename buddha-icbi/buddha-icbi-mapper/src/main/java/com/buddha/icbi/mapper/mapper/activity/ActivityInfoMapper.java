package com.buddha.icbi.mapper.mapper.activity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.icbi.pojo.activity.ActivityInfo;


  /**
 * 
 * 线下活动信息 Mapper 接口
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
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {
	
	/**
	 * 附件会员 审核通过的
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @param value
	 * @param keyword
	 * @return
	 */
	List<ActivityInfo> nearCompany(
			@Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude,
			@Param("distance") BigDecimal distance,
			@Param("status") Integer status,
			@Param("keyword") String keyword);

}