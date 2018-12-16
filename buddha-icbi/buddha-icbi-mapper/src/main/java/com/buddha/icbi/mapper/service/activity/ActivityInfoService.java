package com.buddha.icbi.mapper.service.activity;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.enums.CancelEnum;
import com.buddha.icbi.common.enums.ActivityInfoStatusEnum;
import com.buddha.icbi.common.param.activity.ActivityInfoParam;
import com.buddha.icbi.mapper.mapper.activity.ActivityInfoMapper;
import com.buddha.icbi.pojo.activity.ActivityInfo;


 /**
 * 
 * 线下活动信息 服务实现类
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网科技有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Service
public class ActivityInfoService extends ServiceImpl<ActivityInfoMapper, ActivityInfo> {
	
	/**
	 * 保存活动信息
	 * @param param
	 */
	public void saveActivity(ActivityInfoParam param) {
		// 
		Date curDate = new Date();
		ActivityInfo activity = new ActivityInfo();
		BeanUtils.copyProperties(param, activity);
		activity.setUpdateTime(curDate);
		activity.setCreateTime(curDate);
		// 登记中
		activity.setStatus(ActivityInfoStatusEnum.ENROLLMENT.getValue());
		// 正常进行
		activity.setIsCancel(CancelEnum.NORMAL.getValue());
		super.save(activity);
	}
	
}
