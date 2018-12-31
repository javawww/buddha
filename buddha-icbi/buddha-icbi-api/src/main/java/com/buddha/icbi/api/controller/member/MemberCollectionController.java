package com.buddha.icbi.api.controller.member;

import java.util.Date;
import java.util.List;

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
import com.buddha.icbi.common.param.member.MemberCollectionParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.mapper.service.member.MemberCollectionService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberCollection;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;

/**
 * //在这里注释类文件的作用等信息
 *
 * <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 佛学禅语： <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 
 * 
 * @作者 chuck
 * @时间 2018年12月4日
 * @版权 深圳市佛系派互联网科技集团
 */
@CrossOrigin
@Api(value = "会员信息controller", tags = { "会员信息接口" })
@RestController
@RequestMapping("member-collection")
@Log4j2
public class MemberCollectionController extends WebBaseController {

	@Autowired
	private MemberCollectionService collectionService;
	
	@Autowired
	private CompanyInfoService	companyService;

	/**
	 * 收藏名片列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson listCollection(@RequestBody MemberCollectionParam param) {
		try {
			if (StringUtils.isNull(param.getMemberId())) {
				log.info("会员Id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<MemberCollection> queryWrapper = super.getQueryWrapper(MemberCollection.class);
			queryWrapper.getEntity().setCreateId(param.getMemberId());
			// 关键字查询
			if(StringUtils.isNotNull(param.getKeyword())) {
				String[] idArr = companyService.getIdArr(param.getKeyword());
				queryWrapper.in("company_id", idArr);
			}
			queryWrapper.orderByDesc(true, "create_time");
			List<MemberCollection> list = collectionService.list(queryWrapper);
			if(StringUtils.isNotEmpty(list)) {
				for (MemberCollection collection : list) {
					// 获取公司对象信息
					CompanyInfo company = companyService.getById(collection.getCompanyId());
					collection.setCompanyInfo(company);
				}
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}

	/**
	 * 收藏名片
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("add")
	public ResultJson addCollection(@RequestBody MemberCollectionParam param) {
		try {
			if (StringUtils.isNull(param.getId())) {
				log.info("会员Id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "会员Id为空");
			}
			if (StringUtils.isNull(param.getMemberId())) {
				log.info("收藏会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR, "收藏会员id为空");
			}
			// 保存
			MemberCollection collection = new MemberCollection();
			collection.setMemberId(param.getMemberId());
			collection.setCreateId(param.getId());
			collection.setCreateTime(new Date());
			collectionService.save(collection);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}

	/**
	 * 收藏名片
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("collect")
	public ResultJson collectCompany(@RequestBody MemberCollectionParam param) {
		try {
			// 判断
			if (StringUtils.isNull(param.getCreateId())) {
				log.info("创建人id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if (StringUtils.isNull(param.getCompanyId())) {
				log.info("公司id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if (StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询是否已经收藏
			QueryWrapper<MemberCollection> queryWrapper = super.getQueryWrapper(MemberCollection.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.getEntity().setCompanyId(param.getCompanyId());
			MemberCollection m_collection = collectionService.getOne(queryWrapper);
			if (StringUtils.isNotEmpty(m_collection)) {
				log.info("已经收藏过了");
				return new ResultJson(ResultStatusEnum.DATA_IS_EXIST);
			}
			// 时间
			Date curDate = new Date();
			MemberCollection mc = new MemberCollection();
			mc.setCompanyId(param.getCompanyId());
			mc.setMemberId(param.getMemberId());
			mc.setCreateId(param.getCreateId());
			mc.setCreateTime(curDate);
			// 保存
			collectionService.save(mc);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}

	/**
	 * 取消收藏
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("uncollect")
	public ResultJson unCollectionCompany(@RequestBody MemberCollectionParam param) {
		try {
			// 判断
			if (StringUtils.isNull(param.getCreateId())) {
				log.info("创建人id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if (StringUtils.isNull(param.getCompanyId())) {
				log.info("公司id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 删除
			QueryWrapper<MemberCollection> queryWrapper = super.getQueryWrapper(MemberCollection.class);
			queryWrapper.getEntity().setCompanyId(param.getCompanyId());
			queryWrapper.getEntity().setMemberId(param.getMemberId());
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			collectionService.remove(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}

	/**
	 * 是否收藏过
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("iscollect")
	public ResultJson isCollection(@RequestBody MemberCollectionParam param) {
		try {
			// 判断
			if (StringUtils.isNull(param.getCreateId())) {
				log.info("创建人id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if (StringUtils.isNull(param.getCompanyId())) {
				log.info("公司id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询是否已经收藏
			QueryWrapper<MemberCollection> queryWrapper = super.getQueryWrapper(MemberCollection.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.getEntity().setCompanyId(param.getCompanyId());
			MemberCollection m_collection = collectionService.getOne(queryWrapper);
			if (StringUtils.isNotEmpty(m_collection)) {
				log.info("已经收藏过了");
				return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,(Object)true);
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,(Object)false);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
