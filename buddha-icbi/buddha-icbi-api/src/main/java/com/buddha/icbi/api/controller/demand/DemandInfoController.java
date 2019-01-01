package com.buddha.icbi.api.controller.demand;

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
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.demand.DemandInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.mapper.service.demand.DemandInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.demand.DemandInfo;

import lombok.extern.log4j.Log4j2;

/**
 * //采购信息-控制器
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
@RequestMapping("demand-info")
@Log4j2
public class DemandInfoController extends WebBaseController{

	@Autowired
	private DemandInfoService demandService;
	
	@Autowired
	private CompanyInfoService companyService;
	
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveNews(@RequestBody DemandInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("创建人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"创建人为空");
			}
			if(StringUtils.isNull(param.getMobile())) {
				log.info("联系方式为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"联系方式为空");
			}
			if(StringUtils.isNull(param.getContactName())) {
				log.info("联系人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"联系人为空");
			}
			if(StringUtils.isNull(param.getProductName())) {
				log.info("产品名称为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品名称为空");
			}
			if(StringUtils.isNull(param.getProductImg())) {
				log.info("产品图片为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品图片为空");
			}
			if(StringUtils.isNull(param.getProductDesc())) {
				log.info("产品描述为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品描述为空");
			}
			if(StringUtils.isNull(param.getAmount())) {
				log.info("数量为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"数量为空");
			}
			if(StringUtils.isEmpty(param.getReceiveLatitude())) {
				log.info("收货坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			if(StringUtils.isEmpty(param.getReceiveLongitude())) {
				log.info("收货坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			if(StringUtils.isNull(param.getAddressDetail())) {
				log.info("收货地址为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			// 保存
			Date curDate = new Date();
			DemandInfo demand = new DemandInfo();
			BeanUtils.copyProperties(param, demand);
			// 待审核
			demand.setStatus(AuditEnum.AUDITING.getValue());
			demand.setCreateTime(curDate);
			demand.setUpdateTime(curDate);
			demandService.save(demand);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
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
	public ResultJson listPerson(@RequestBody DemandInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<DemandInfo> queryWrapper = super.getQueryWrapper(DemandInfo.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.orderByDesc("update_time");
			List<DemandInfo> demands = demandService.list(queryWrapper);
			if(StringUtils.isNotEmpty(demands)) {
				for (DemandInfo demand : demands) {
					// 产品图片处理
					if(StringUtils.isNotNull(demand.getProductImg())) {
						List<FileList> fileList = new ArrayList<FileList>();
						String str[] = StringUtils.string2List(demand.getProductImg());
						for (int i = 0; i < str.length; i++) {
							FileList file = new FileList();
							file.setUrl(str[i]);
							file.setOssFileUrl(str[i]);
							file.setStatus("done");
							file.setUid(i);
							fileList.add(file);
						}
						demand.setProductImgArr(fileList);
					}
				}
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, demands);
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
	public ResultJson listWaitAudit(@RequestBody DemandInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<DemandInfo> queryWrapper = super.getQueryWrapper(DemandInfo.class);
			// 待审核
			queryWrapper.getEntity().setStatus(AuditEnum.AUDITING.getValue());
			queryWrapper.orderByDesc("update_time");
			List<DemandInfo> demands = demandService.list(queryWrapper);
			if(StringUtils.isNotEmpty(demands)) {
				for (DemandInfo demand : demands) {
					// 产品图片处理
					if(StringUtils.isNotNull(demand.getProductImg())) {
						List<FileList> fileList = new ArrayList<FileList>();
						String str[] = StringUtils.string2List(demand.getProductImg());
						for (int i = 0; i < str.length; i++) {
							FileList file = new FileList();
							file.setUrl(str[i]);
							file.setOssFileUrl(str[i]);
							file.setStatus("done");
							file.setUid(i);
							fileList.add(file);
						}
						demand.setProductImgArr(fileList);
					}
				}
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, demands);
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
	public ResultJson passOpt(@RequestBody DemandInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			DemandInfo demand = demandService.getById(param.getId());
			if(null == demand) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			if(demand.getStatus() != AuditEnum.AUDITING.getValue()) {
				log.info("非待审核状态");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 审核通过
			demand.setStatus(AuditEnum.AUDITED.getValue());
			demand.setUpdateTime(new Date());
			demandService.updateById(demand);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 详情
	 * @param param
	 * @return
	 */
	@PostMapping("detail")
	public ResultJson detailNews(@RequestBody DemandInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 详情
			DemandInfo demand = demandService.getById(param.getId());
			if(null == demand) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			// 产品图片处理
			if(StringUtils.isNotNull(demand.getProductImg())) {
				List<FileList> fileList = new ArrayList<FileList>();
				String str[] = StringUtils.string2List(demand.getProductImg());
				for (int i = 0; i < str.length; i++) {
					FileList file = new FileList();
					file.setUrl(str[i]);
					file.setOssFileUrl(str[i]);
					file.setStatus("done");
					file.setUid(i);
					fileList.add(file);
				}
				demand.setProductImgArr(fileList);
			}
			// 单位名称
			QueryWrapper<CompanyInfo> queryWrapper = super.getQueryWrapper(CompanyInfo.class);
			queryWrapper.getEntity().setMemberId(demand.getCreateId());
			CompanyInfo company = companyService.getOne(queryWrapper);
			if(null != company) {
				demand.setCompanyName(company.getCompanyName());
				demand.setRealAvatar(company.getRealAvatar());
				demand.setCompanyId(company.getId());
			}else {
				demand.setCompanyName("");
				demand.setRealAvatar("");
				demand.setCompanyId("");
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, demand);
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
	public ResultJson delNews(@RequestBody DemandInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 删除
			demandService.removeById(param.getId());
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	/**
	 * 编辑
	 * @param param
	 * @return
	 */
	@PostMapping("edit")
	public ResultJson editNews(@RequestBody DemandInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("创建人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"创建人为空");
			}
			if(StringUtils.isNull(param.getMobile())) {
				log.info("联系方式为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"联系方式为空");
			}
			if(StringUtils.isNull(param.getContactName())) {
				log.info("联系人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"联系人为空");
			}
			if(StringUtils.isNull(param.getProductName())) {
				log.info("产品名称为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品名称为空");
			}
			if(StringUtils.isNull(param.getProductImg())) {
				log.info("产品图片为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品图片为空");
			}
			if(StringUtils.isNull(param.getProductDesc())) {
				log.info("产品描述为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品描述为空");
			}
			if(StringUtils.isNull(param.getAmount())) {
				log.info("数量为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"数量为空");
			}
			if(StringUtils.isEmpty(param.getReceiveLatitude())) {
				log.info("收货坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			if(StringUtils.isEmpty(param.getReceiveLongitude())) {
				log.info("收货坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			if(StringUtils.isNull(param.getAddressDetail())) {
				log.info("收货地址为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"收货地址为空");
			}
			// 查询
			DemandInfo demand = demandService.getById(param.getId());
			// 编辑
			Date curDate = new Date();
			BeanUtils.copyProperties(param, demand);
			demand.setUpdateTime(curDate);
			demandService.updateById(demand);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 查询附近列表
	 * @param param
	 * @return
	 */
	@PostMapping("list-search")
	public ResultJson listSearch(@RequestBody DemandInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			List<DemandInfo> demands = demandService.listSearch(param);
			if(StringUtils.isNotEmpty(demands)) {
				for (DemandInfo demand : demands) {
					// 产品图片处理
					if(StringUtils.isNotNull(demand.getProductImg())) {
						List<FileList> fileList = new ArrayList<FileList>();
						String str[] = StringUtils.string2List(demand.getProductImg());
						for (int i = 0; i < str.length; i++) {
							FileList file = new FileList();
							file.setUrl(str[i]);
							file.setOssFileUrl(str[i]);
							file.setStatus("done");
							file.setUid(i);
							fileList.add(file);
						}
						demand.setProductImgArr(fileList);
					}
					// 单位名称
					QueryWrapper<CompanyInfo> queryWrapper = super.getQueryWrapper(CompanyInfo.class);
					queryWrapper.getEntity().setMemberId(demand.getCreateId());
					CompanyInfo company = companyService.getOne(queryWrapper);
					if(null != company) {
						demand.setCompanyName(company.getCompanyName());
						demand.setRealAvatar(company.getRealAvatar());
						demand.setCompanyId(company.getId());
					}else {
						demand.setCompanyName("");
						demand.setRealAvatar("");
						demand.setCompanyId("");
					}
				}
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, demands);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
