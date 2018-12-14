package com.buddha.icbi.api.controller.activity;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.CancelEnum;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.enums.ActivityInfoStatus;
import com.buddha.icbi.common.param.activity.ActivityInfoParam;
import com.buddha.icbi.mapper.service.activity.ActivityInfoService;
import com.buddha.icbi.pojo.activity.ActivityInfo;

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
	@PostMapping("save")
	public ResultJson saveActivity(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			if(StringUtils.isNull(param.getTheme())) {
				log.info("活动主题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动主题为空");
			}
			if(StringUtils.isNull(param.getAmount())) {
				log.info("参与人数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"参与人数为空");
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("位置为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"位置为空");
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("位置为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"位置为空");
			}
			if(StringUtils.isNull(param.getPlace())) {
				log.info("具体位置为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"具体位置为空");
			}
			if(StringUtils.isNull(param.getChargeType())) {
				log.info("收费类型为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收费类型为空");
			}
			if(StringUtils.isNull(param.getPost())) {
				log.info("活动海报为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动海报为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("活动介绍为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动介绍为空");
			}
			// 保存
			activityService.saveActivity(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新活动信息
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public ResultJson updateActivity(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("活动id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动id为空");
			}
			// 更新
			ActivityInfo activity = activityService.getById(param.getId());
			if(null == activity) {
				log.info("活动数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"活动数据不存在");
			}
			BeanUtils.copyProperties(param, activity);
			activity.setUpdateTime(new Date());
			activityService.updateById(activity);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 取消活动
	 * @param param
	 * @return
	 */
	@PostMapping("cancel")
	public ResultJson cancelActivity(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("活动id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动id为空");
			}
			if(StringUtils.isNull(param.getCancelExplain())) {
				log.info("取消原因为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"取消原因为空");
			}
			// 取消
			ActivityInfo activity = activityService.getById(param.getId());
			if(null == activity) {
				log.info("活动数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"活动数据不存在");
			}
			if(activity.getStatus().intValue() != ActivityInfoStatus.ENROLLMENT.getValue().intValue()) {
				log.info("活动非报名中,不能取消");
				return new ResultJson(ResultStatusEnum.COMMON_FAIL,"活动非报名中,不能取消");
			}
			// 取消
			activity.setIsCancel(CancelEnum.CANCEL.getValue());
			activity.setCancelExplain(param.getCancelExplain());
			activity.setUpdateTime(new Date());
			// 发送取消通知
			// xxxx
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 活动详情
	 * @param param
	 * @return
	 */
	@PostMapping("detail")
	public ResultJson detailActivity(@RequestBody ActivityInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				log.info("活动id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动id为空");
			}
			// 查询
			ActivityInfo activity = activityService.getById(param.getId());
			if(null == activity) {
				log.info("活动数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"活动数据不存在");
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, activity);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
		
	}
}
