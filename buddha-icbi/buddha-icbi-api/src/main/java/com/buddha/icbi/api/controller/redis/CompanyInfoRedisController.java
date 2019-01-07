package com.buddha.icbi.api.controller.redis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.FastJsonUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.component.redis.service.RedisService;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.api.controller.company.CompanyInfoController;
import com.buddha.icbi.common.constant.RedisKeyConstant;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;

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
@RestController
@RequestMapping("redis-company-info")
@Log4j2
public class CompanyInfoRedisController extends WebBaseController{

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private CompanyInfoService companyService;
	
	/**
	 * 更新缓存数据
	 * @param param
	 * @return
	 */
	@PostMapping("update")
	public ResultJson updateCompanyInfoRedis(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员Id为空");
				//return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员Id为空");
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
			// 查询
			String jsonStr = redisService.get(RedisKeyConstant.NEAR_COMPANY_INFO_KEY+param.getMemberId());
			if(StringUtils.isNull(jsonStr)) {
				// 附近会员列表
				List<MemberLocationDto> dtoList = companyService.listNearCompany(param);
				// 更新缓存
				redisService.set(RedisKeyConstant.NEAR_COMPANY_INFO_KEY+param.getMemberId(), JSON.toJSONString(dtoList));
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 查询缓存数据
	 * @param param
	 * @return
	 */
	@PostMapping("query")
	public ResultJson queryCompanyInfoRedis(@RequestBody CompanyInfoParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员Id为空");
				//return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员Id为空");
			}
			// 根据KEY 查询
			String jsonStr = redisService.get(RedisKeyConstant.NEAR_COMPANY_INFO_KEY+param.getMemberId());
			List<MemberLocationDto> dtoList = FastJsonUtils.toList(jsonStr, MemberLocationDto.class);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, dtoList);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}
