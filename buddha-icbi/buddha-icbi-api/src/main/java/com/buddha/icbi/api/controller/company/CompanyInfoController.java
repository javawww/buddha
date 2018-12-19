package com.buddha.icbi.api.controller.company;

import java.util.Date;

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
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;

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

	/**
	 * 公司详情信息
	 * @param param
	 * @return
	 */
	@PostMapping("detail")
	public ResultJson detailCompany(@RequestBody CompanyInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 查询
			QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
			queryWrapper.getEntity().setMemberId(param.getMemberId());
			CompanyInfo company = companyService.getOne(queryWrapper);
			if(null == company) {
				log.info("公司信息为空");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST,"公司信息为空");
			}
			// 产品标签
			String companyTag = company.getCompanyTag();
			company.setCompanyTag(StringUtils.vertical2comma(companyTag));
			company.setRealAvatarArr(StringUtils.string2List(company.getRealAvatar()));
			company.setIdentityFrontArr(StringUtils.string2List(company.getIdentityFront()));
			company.setIdentityBackArr(StringUtils.string2List(company.getIdentityBack()));
			company.setCompanyProductArr(StringUtils.string2List(company.getCompanyProduct()));
			company.setCompanyLicenseArr(StringUtils.string2List(company.getCompanyLicense()));
			company.setCompanyLogoArr(StringUtils.string2List(company.getCompanyLogo()));
			company.setCompanyEnvImgArr(StringUtils.string2List(company.getCompanyEnvImg()));
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, company);
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
}
