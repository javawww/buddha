package com.buddha.icbi.api.controller.company;

import java.util.ArrayList;
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
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.enums.IsAdminEnum;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.mapper.service.member.MemberInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.member.MemberInfo;

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
@Api(value = "公司信息controller",tags = {"公司信息接口"})
@RestController
@RequestMapping("company-info")
@Log4j2
public class CompanyInfoController extends WebBaseController{
	
	@Autowired
	private CompanyInfoService companyService;

	@Autowired
	private MemberInfoService	memberService;
	/**
	 * 公司详情信息
	 * @param param
	 * @return
	 */
	@PostMapping("detail")
	public ResultJson detailCompany(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"id为空");
			}
			// 查询
			CompanyInfo company = companyService.detailCompany(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, company);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	/**
	 * 根据会员id查询公司详情
	 * @param param
	 * @return
	 */
	@PostMapping("detail-mid")
	public ResultJson detailCompanyByMid(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 查询
			CompanyInfo company = companyService.detailCompanyByMid(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, company);
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
	@PostMapping("wait-audit")
	public ResultJson waitAuditList(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("memberId为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"memberId为空");
			}
			// 会员对象
			MemberInfo member = memberService.getById(param.getMemberId());
			if(null == member) {
				log.info("会员不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"会员不存在");
			}
			if(member.getIsAdmin() == IsAdminEnum.ORDINARY_MEMBER.getValue()) {
				log.info("权限不足");
				return new ResultJson(ResultStatusEnum.NOT_AUTHOR,"权限不足");
			}
			// 待审核列表
			QueryWrapper<CompanyInfo> queryWrapper = super.getQueryWrapper(CompanyInfo.class);
			queryWrapper.getEntity().setIsCertification(AuditEnum.AUDITING.getValue());
			queryWrapper.orderByDesc(true, "create_time");
			List<CompanyInfo> list = companyService.list(queryWrapper);
			if(StringUtils.isEmpty(list)) {
				log.info("待审核列表为空");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"待审核列表为空");
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 保存公司信息
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveCompany(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 保存
			Date curDate = new Date();
			CompanyInfo company = new CompanyInfo();
			BeanUtils.copyProperties(param, company);
			company.setCreateTime(curDate);
			company.setUpdateTime(curDate);
			companyService.save(company);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public ResultJson updateConpany(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				log.info("公司id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"公司id为空");
			}
			// 更新
			CompanyInfo company = companyService.getById(param.getId());
			if(null == company) {
				log.info("公司数据为空");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"公司数据为空");
			}
			BeanUtils.copyProperties(param, company);
			company.setUpdateTime(new Date());
			companyService.updateById(company);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 删除公司信息
	 * @param param
	 * @return
	 */
	@PostMapping("remove")
	public ResultJson removeCompany(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getId())) {
				log.info("公司id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"公司id为空");
			}
			// 删除
			companyService.removeById(param.getId());
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 审核通过
	 * @param param
	 * @return
	 */
	@PostMapping("pass")
	public ResultJson passCertification(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"id为空");
			}
			// 公司对象
			CompanyInfo company = companyService.getById(param.getId());
			if(null == company) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"数据不存在");
			}
			if(company.getIsCertification() == AuditEnum.AUDITED.getValue()) {
				log.info("不能重复通过");
				return new ResultJson(ResultStatusEnum.COMMON_FAIL,"不能重复通过");
			}
			company.setIsCertification(AuditEnum.AUDITED.getValue());
			company.setUpdateTime(new Date());
			companyService.updateById(company);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 审核拒绝
	 * @param param
	 * @return
	 */
	@PostMapping("reject")
	public ResultJson rejectCertification(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"id为空");
			}
			// 公司对象
			CompanyInfo company = companyService.getById(param.getId());
			if(null == company) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"数据不存在");
			}
			if(company.getIsCertification() == AuditEnum.REFUSE.getValue()) {
				log.info("不能重复拒绝");
				return new ResultJson(ResultStatusEnum.COMMON_FAIL,"不能重复拒绝");
			}
			company.setIsCertification(AuditEnum.REFUSE.getValue());
			company.setUpdateTime(new Date());
			companyService.updateById(company);
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
	public ResultJson listNearCompany(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员Id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员Id为空");
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("纬度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"纬度为空");
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("经度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"经度为空");
			}
			// 附近会员列表
			List<MemberLocationDto> dtoList = companyService.listNearCompany(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, dtoList);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 查询附近会员列表
	 * 查询关键字：1-公司标签
	 * @param param
	 * @return
	 */
	@PostMapping("search-company")
	public ResultJson listSearchCompany(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员Id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员Id为空");
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("纬度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"纬度为空");
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("经度为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"经度为空");
			}
			if(StringUtils.isNull(param.getKeyword())) {
				log.info("关键字为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"关键字为空");
			}
			// 查询附近会员列表
			List<MemberLocationDto> dtoList = companyService.listSearchCompany(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, dtoList);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
