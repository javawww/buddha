package com.buddha.icbi.mapper.service.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.constant.OSSImageStyleConstant;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.activity.ActivityInfoParam;
import com.buddha.icbi.mapper.mapper.activity.ActivityInfoMapper;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.pojo.activity.ActivityInfo;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberInfo;

import lombok.extern.log4j.Log4j2;


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
@Log4j2
public class ActivityInfoService extends ServiceImpl<ActivityInfoMapper, ActivityInfo> {
	
	@Autowired
	private ActivityInfoMapper activityMapper;
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	/**
	 * 保存活动信息
	 * @param param
	 */
	public void saveActivity(ActivityInfoParam param) {
		// 参数
		Date curDate = new Date();
		ActivityInfo activity = new ActivityInfo();
		BeanUtils.copyProperties(param, activity);
		activity.setStatus(AuditEnum.AUDITING.getValue());//状态
		activity.setUpdateTime(curDate);
		activity.setCreateTime(curDate);
		super.save(activity);
	}
	/**
	 * 附近活动
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> listNearCompany(ActivityInfoParam param) {
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		Integer id = 0;
		// 附近五公里
		List<ActivityInfo> activitys = activityMapper.nearCompany(
				param.getLatitude(), param.getLongitude(), param.getDistance(), AuditEnum.AUDITED.getValue(), param.getKeyword());
		// 当前会员
		MemberInfo _member = memberMapper.selectById(param.getCreateId());
		if(null == _member) {
			log.info("当前会员不存在");
			//throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"当前会员不存在");
		}
		 
		// 附近会员
		if(StringUtils.isNotNull(activitys)) {
			for (ActivityInfo activity : activitys) {
				MemberLocationDto dto = new MemberLocationDto();
				dto.setAddress(activity.getAddress());
				dto.setHeight(32);
				dto.setWidth(32);
				dto.setIconPath(activity.getCoverImg() + OSSImageStyleConstant.IMAGE_CIRCLE);
				// 查询
				dto.setMemberId(activity.getCreateId());
				dto.setActivityId(activity.getId());
				dto.setId(id);
				id ++;
				dto.setLongitude(activity.getLongitude());
				dto.setLatitude(activity.getLatitude());
				dto.setName(activity.getTheme());
				dto.setDistance(activity.getDistance()); // 距离单位公里
				
				// 公司信息
				QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
				queryWrapper.getEntity().setMemberId(activity.getCreateId());
				CompanyInfo company = companyMapper.selectOne(queryWrapper);
				dto.setRealName(company.getRealName());
				dto.setRealAvatar(company.getRealAvatar());
				dto.setMobile(company.getMobile());
				dto.setLandlineNumber(company.getLandlineNumber());
				dto.setFirstName(company.getFirstName());
				dto.setLastName(company.getLastName());
				dto.setGender(company.getGender());
				
				dtoList.add(dto);
			}
		}else {
			
		}
		// 放置list
		return dtoList;
	}
	
}
