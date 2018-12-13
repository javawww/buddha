package com.buddha.icbi.api.controller.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.param.activity.ActivityInfoParam;
import com.buddha.icbi.mapper.service.activity.ActivityInfoService;

import lombok.extern.log4j.Log4j2;

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
@RestController
@RequestMapping("activity-info")
@Log4j2
public class ActivityInfoController extends WebBaseController{

	@Autowired
	private ActivityInfoService activityService;
	
	/**
	 * 发起活动
	 * @param param
	 * @return
	 */
	@RequestMapping("sponsor")
	public ResultJson sponsorActivity(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId参数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId参数为空");
			}
			if(StringUtils.isNull(param.getTheme())) {
				log.info("活动主题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动主题为空");
			}
			if(StringUtils.isNull(param.getAmount())) {
				log.info("参与人数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"参与人数为空");
			}
			
			return null;
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}
