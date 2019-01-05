package com.buddha.icbi.api.controller.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.CancelEnum;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.DateTimeUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.enums.ActivityInfoStatusEnum;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.activity.ActivityInfoParam;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.common.param.demand.DemandInfoParam;
import com.buddha.icbi.common.param.job.JobInfoParam;
import com.buddha.icbi.mapper.service.activity.ActivityInfoService;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.pojo.activity.ActivityInfo;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.demand.DemandInfo;
import com.buddha.icbi.pojo.job.JobInfo;

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
	
	@Autowired
	private CompanyInfoService companyService;
	/**
	 * 发起活动
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveActivity(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("活动地点为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动地点为空");
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("活动地点为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动地点为空");
			}
			if(StringUtils.isNull(param.getAddress())) {
				log.info("活动地点为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动地点为空");
			}
			if(StringUtils.isNull(param.getAddressDetail())) {
				log.info("详细地点为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"详细地点为空");
			}
			if(StringUtils.isNull(param.getTheme())) {
				log.info("活动主题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动主题为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("活动介绍为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动介绍为空");
			}
			if(StringUtils.isEmpty(param.getHoldTime())) {
				log.info("举办时间为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"举办时间为空");
			}
			if(StringUtils.isEmpty(param.getOverTime())) {
				log.info("结束时间为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"结束时间为空");
			}
			if(StringUtils.isNull(param.getAmount())) {
				log.info("参与人数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"参与人数为空");
			}
			if(StringUtils.isNull(param.getChargeDesc())) {
				log.info("收费类型为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收费类型为空");
			}
			if(StringUtils.isNull(param.getCoverImg())) {
				log.info("活动封面为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"活动封面为空");
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
			if(activity.getStatus().intValue() != ActivityInfoStatusEnum.ENROLLMENT.getValue().intValue()) {
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
			// 举办时间
			if(StringUtils.isNotEmpty(activity.getHoldTime())) {
				activity.setHodeTimetxt(DateTimeUtils.getDateTimeFormatToString(activity.getHoldTime(), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_CHINA));
			}
			// 结束时间
			if(StringUtils.isNotEmpty(activity.getOverTime())) {
				activity.setOverTimetxt(DateTimeUtils.getDateTimeFormatToString(activity.getOverTime(), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_CHINA));
			}
			// 单位名称
			QueryWrapper<CompanyInfo> queryWrapper = super.getQueryWrapper(CompanyInfo.class);
			queryWrapper.getEntity().setMemberId(activity.getCreateId());
			CompanyInfo company = companyService.getOne(queryWrapper);
			if(null != company) {
				activity.setCompanyName(company.getCompanyName());
				activity.setRealAvatar(company.getRealAvatar());
				activity.setCompanyId(company.getId());
				activity.setCompanyProfile(company.getCompanyProfile());
			}else {
				activity.setCompanyName("");
				activity.setRealAvatar("");
				activity.setCompanyId("");
				activity.setCompanyProfile("");
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, activity);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
		
	}
	
	/**
	 * 待审核列表
	 * @param param
	 * @return
	 */
	@PostMapping("list-audit")
	public ResultJson listWaitAudit(@RequestBody ActivityInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<ActivityInfo> queryWrapper = super.getQueryWrapper(ActivityInfo.class);
			// 待审核
			queryWrapper.getEntity().setStatus(AuditEnum.AUDITING.getValue());
			queryWrapper.orderByDesc("update_time");
			List<ActivityInfo> activity = activityService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, activity);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 个人列表
	 * @param param
	 * @return
	 */
	@PostMapping("list-person")
	public ResultJson listPerson(@RequestBody ActivityInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<ActivityInfo> queryWrapper = super.getQueryWrapper(ActivityInfo.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.orderByDesc("update_time");
			List<ActivityInfo> activitys = activityService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, activitys);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 通过
	 * @param param
	 * @return
	 */
	@PostMapping("pass")
	public ResultJson passOpt(@RequestBody ActivityInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			ActivityInfo activity = activityService.getById(param.getId());
			if(null == activity) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			if(activity.getStatus() != AuditEnum.AUDITING.getValue()) {
				log.info("非待审核状态");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 审核通过
			activity.setStatus(AuditEnum.AUDITED.getValue());
			activity.setUpdateTime(new Date());
			activityService.updateById(activity);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 删除
	 * @param param
	 * @return
	 */
	@PostMapping("del")
	public ResultJson delActivity(@RequestBody ActivityInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 删除
			activityService.removeById(param.getId());
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	
	/**
	 * 首页附近会员列表
	 * @param param
	 * @return
	 */
	@PostMapping("near-company")
	public ResultJson listNearCompany(@RequestBody ActivityInfoParam param) {
		try {
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员Id为空");
//				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员Id为空");
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("纬度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"纬度为空");
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("经度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"经度为空");
			}
			/*if(StringUtils.isEmpty(param.getDistance())) {
				log.info("距离为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"距离为空");
			}*/
			// 附近会员列表
			List<MemberLocationDto> dtoList = activityService.listNearCompany(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, dtoList);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
