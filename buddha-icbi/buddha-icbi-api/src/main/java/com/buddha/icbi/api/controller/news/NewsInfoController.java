package com.buddha.icbi.api.controller.news;

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
import com.buddha.component.common.util.DateTimeUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.news.NewsInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.mapper.service.news.NewsInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.news.NewsInfo;

import lombok.extern.log4j.Log4j2;

/**
 * //风采展示-控制器
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
@RequestMapping("news-info")
@Log4j2
public class NewsInfoController extends WebBaseController{
	
	@Autowired
	private NewsInfoService newsService;
	
	@Autowired
	private CompanyInfoService	companyService;
	
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveNews(@RequestBody NewsInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("创建人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"创建人为空");
			}
			if(StringUtils.isNull(param.getType())) {
				log.info("风采类型为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采类型为空");
			}
			if(StringUtils.isNull(param.getTitle())) {
				log.info("风采标题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采标题为空");
			}
			if(StringUtils.isNull(param.getCoverImg())) {
				log.info("风采封面为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采封面为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("风采内容为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采内容为空");
			}
			// 保存
			Date curDate = new Date();
			NewsInfo news = new NewsInfo();
			BeanUtils.copyProperties(param, news);
			// 待审核
			news.setStatus(AuditEnum.AUDITING.getValue());
			news.setCreateTime(curDate);
			news.setUpdateTime(curDate);
			newsService.save(news);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 个人风采列表
	 * @param param
	 * @return
	 */
	@PostMapping("list-person")
	public ResultJson listPerson(@RequestBody NewsInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<NewsInfo> queryWrapper = super.getQueryWrapper(NewsInfo.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.orderByDesc("update_time");
			List<NewsInfo> news = newsService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, news);
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
	public ResultJson listWaitAudit(@RequestBody NewsInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<NewsInfo> queryWrapper = super.getQueryWrapper(NewsInfo.class);
			// 待审核
			queryWrapper.getEntity().setStatus(AuditEnum.AUDITING.getValue());
			queryWrapper.orderByDesc("update_time");
			List<NewsInfo> news = newsService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, news);
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
	public ResultJson passOpt(@RequestBody NewsInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			NewsInfo news = newsService.getById(param.getId());
			if(null == news) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			if(news.getStatus() != AuditEnum.AUDITING.getValue()) {
				log.info("非待审核状态");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 审核通过
			news.setStatus(AuditEnum.AUDITED.getValue());
			news.setUpdateTime(new Date());
			newsService.updateById(news);
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
	public ResultJson detailNews(@RequestBody NewsInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 详情
			NewsInfo news = newsService.getById(param.getId());
			if(null == news) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			// 封面图片处理
			if(StringUtils.isNotNull(news.getCoverImg())) {
				List<FileList> fileList = new ArrayList<FileList>();
				String str[] = StringUtils.string2List(news.getCoverImg());
				for (int i = 0; i < str.length; i++) {
					FileList file = new FileList();
					file.setUrl(str[i]);
					file.setOssFileUrl(str[i]);
					file.setStatus("done");
					file.setUid(i);
					fileList.add(file);
				}
				news.setCoverImgArr(fileList);
			}
			// 时间格式化
			news.setCreateTimetxt(DateTimeUtils.getDateTimeFormatToString(news.getCreateTime(), DateTimeUtils.FORMAT_YYYY_MM_DD_CHINA));
			// 单位名称
			CompanyInfo company =  companyService.getCompanyInfoByMid(news.getCreateId());
			if(null != company) {
				news.setCompanyName(company.getCompanyName());
				news.setRealAvatar(company.getRealAvatar());
				news.setCompanyId(company.getId());
				news.setCompanyProfile(company.getCompanyProfile());
			}else {
				news.setCompanyName("");
				news.setRealAvatar("");
				news.setCompanyId("");
				news.setCompanyProfile("");
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, news);
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
	public ResultJson delNews(@RequestBody NewsInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 删除
			newsService.removeById(param.getId());
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
	public ResultJson editNews(@RequestBody NewsInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"id为空");
			}
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("创建人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"创建人为空");
			}
			if(StringUtils.isNull(param.getType())) {
				log.info("风采类型为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采类型为空");
			}
			if(StringUtils.isNull(param.getTitle())) {
				log.info("风采标题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采标题为空");
			}
			if(StringUtils.isNull(param.getCoverImg())) {
				log.info("风采封面为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采封面为空");
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("风采内容为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"风采内容为空");
			}
			// 查询
			NewsInfo news = newsService.getById(param.getId());
			// 编辑
			Date curDate = new Date();
			BeanUtils.copyProperties(param, news);
			news.setUpdateTime(curDate);
			newsService.updateById(news);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 查询附近风采
	 * @param param
	 * @return
	 */
	@PostMapping("list-search")
	public ResultJson listSearch(@RequestBody NewsInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getType())) {
				log.info("类型为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isEmpty(param.getLatitude())) {
				log.info("坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isEmpty(param.getLongitude())) {
				log.info("坐标为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			List<NewsInfo> news = newsService.listSearch(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, news);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}
