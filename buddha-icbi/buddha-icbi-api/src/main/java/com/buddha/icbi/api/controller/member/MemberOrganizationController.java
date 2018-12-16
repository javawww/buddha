package com.buddha.icbi.api.controller.member;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.member.MemberOrganizationParam;
import com.buddha.icbi.mapper.service.member.MemberOrganizationService;
import com.buddha.icbi.pojo.member.MemberOrganization;

import io.swagger.annotations.Api;
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
 * @时间 	2018年12月4日
 * @版权  	深圳市佛系派互联网科技集团
 */
@CrossOrigin
@Api(value = "会员组织信息controller",tags = {"会员组织信息"})
@RestController
@RequestMapping("member-organization")
@Log4j2
public class MemberOrganizationController extends WebBaseController{

	@Autowired
	private MemberOrganizationService organizationService;
	
	/**
	 * 保存组织信息
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveOrganization(@RequestBody List<MemberOrganizationParam> param) {
		try {
			Date curDate = new Date();
			// 判断
			for (MemberOrganizationParam rec : param) {
				if(StringUtils.isNull(rec.getMemberId())) {
					log.info("会员id为空");
					return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
				}
				if(StringUtils.isNull(rec.getType())) {
					log.info("组织类型为空");
					return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"组织类型为空");
				}
				if(StringUtils.isNull(rec.getName())) {
					log.info("组织名称为空");
					return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"组织名称为空");
				}
				// 保存
				MemberOrganization organization = new MemberOrganization();
				BeanUtils.copyProperties(rec, organization);
				// 0-待审核
				organization.setStatus(AuditEnum.AUDITING.getValue());
				organization.setCreateTime(curDate);
				organizationService.save(organization);
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 组织认证-通过
	 * @param param
	 * @return
	 */
	@PostMapping("audited")
	public ResultJson auditedOrganization(@RequestBody MemberOrganizationParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("组织id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"组织id为空");
			}
			// 通过
			MemberOrganization organization = organizationService.getById(param.getId());
			if(null == organization) {
				log.info("组织数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"组织数据不存在");
			}
			organization.setStatus(AuditEnum.AUDITED.getValue());
			organizationService.updateById(organization);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 组织认证-拒绝
	 * @param param
	 * @return
	 */
	@PostMapping("refuse")
	public ResultJson refuseOrganization(@RequestBody MemberOrganizationParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("组织id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"组织id为空");
			}
			if(StringUtils.isNull(param.getRefuse())) {
				log.info("拒绝原因为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"拒绝原因为空");
			}
			// 拒绝
			MemberOrganization organization = organizationService.getById(param.getId());
			if(null == organization) {
				log.info("组织数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"组织数据不存在");
			}
			organization.setStatus(AuditEnum.REFUSE.getValue());
			organization.setRefuse(param.getRefuse());
			organizationService.updateById(organization);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 组织列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson listOrganization(@RequestBody MemberOrganizationParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 列表
			QueryWrapper<MemberOrganization> queryWrapper = new QueryWrapper<MemberOrganization>(new MemberOrganization());
			queryWrapper.getEntity().setMemberId(param.getMemberId());
			List<MemberOrganization> list = organizationService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 审核列表
	 * @param param
	 * @return
	 */
	@PostMapping("audit-list")
	public ResultJson auditListOrganization(@RequestBody MemberOrganizationParam param) {
		try {
			if(StringUtils.isNull(param.getName())) {
				log.info("名称为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"名称为空");
			}
			// 列表
			QueryWrapper<MemberOrganization> queryWrapper = new QueryWrapper<MemberOrganization>(new MemberOrganization());
			queryWrapper.getEntity().setName(param.getName());
			List<MemberOrganization> list = organizationService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
