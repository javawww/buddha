package com.buddha.icbi.api.controller.job;

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
import com.buddha.icbi.common.param.job.JobInfoParam;
import com.buddha.icbi.mapper.service.job.JobInfoService;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.job.JobInfo;

import lombok.extern.log4j.Log4j2;

/**
 * //招聘信息-控制器
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
@RequestMapping("job-info")
@Log4j2
public class JobInfoController extends WebBaseController {

	@Autowired
	private JobInfoService jobService;
	
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveNews(@RequestBody JobInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("创建人为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getTitle())) {
				log.info("招聘标题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getCoverImg())) {
				log.info("招聘封面为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("招聘内容为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getJobDesc())) {
				log.info("招聘岗位为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getSalaryDesc())) {
				log.info("待遇说明为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 保存
			Date curDate = new Date();
			JobInfo job = new JobInfo();
			BeanUtils.copyProperties(param, job);
			// 待审核
			job.setStatus(AuditEnum.AUDITING.getValue());
			job.setCreateTime(curDate);
			job.setUpdateTime(curDate);
			jobService.save(job);
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
	public ResultJson listPerson(@RequestBody JobInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<JobInfo> queryWrapper = super.getQueryWrapper(JobInfo.class);
			queryWrapper.getEntity().setCreateId(param.getCreateId());
			queryWrapper.orderByDesc("update_time");
			List<JobInfo> news = jobService.list(queryWrapper);
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
	public ResultJson listWaitAudit(@RequestBody JobInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getCreateId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			QueryWrapper<JobInfo> queryWrapper = super.getQueryWrapper(JobInfo.class);
			// 待审核
			queryWrapper.getEntity().setStatus(AuditEnum.AUDITING.getValue());
			queryWrapper.orderByDesc("update_time");
			List<JobInfo> news = jobService.list(queryWrapper);
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
	public ResultJson passOpt(@RequestBody JobInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			JobInfo job = jobService.getById(param.getId());
			if(null == job) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			if(job.getStatus() != AuditEnum.AUDITING.getValue()) {
				log.info("非待审核状态");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 审核通过
			job.setStatus(AuditEnum.AUDITED.getValue());
			job.setUpdateTime(new Date());
			jobService.updateById(job);
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
	public ResultJson detailNews(@RequestBody JobInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 详情
			JobInfo job = jobService.getById(param.getId());
			if(null == job) {
				log.info("数据不存在");
				return new ResultJson(ResultStatusEnum.DATA_NOT_EXIST);
			}
			// 封面图片处理
			if(StringUtils.isNotNull(job.getCoverImg())) {
				List<FileList> fileList = new ArrayList<FileList>();
				String str[] = StringUtils.string2List(job.getCoverImg());
				for (int i = 0; i < str.length; i++) {
					FileList file = new FileList();
					file.setUrl(str[i]);
					file.setOssFileUrl(str[i]);
					file.setStatus("done");
					file.setUid(i);
					fileList.add(file);
				}
				job.setCoverImgArr(fileList);
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, job);
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
	public ResultJson delNews(@RequestBody JobInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 删除
			jobService.removeById(param.getId());
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
	public ResultJson editNews(@RequestBody JobInfoParam param) {
		try {
			// 判断 
			if(StringUtils.isNull(param.getId())) {
				log.info("id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getTitle())) {
				log.info("招聘标题为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getCoverImg())) {
				log.info("招聘封面为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getContent())) {
				log.info("招聘内容为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getJobDesc())) {
				log.info("招聘岗位为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			if(StringUtils.isNull(param.getSalaryDesc())) {
				log.info("待遇说明为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR);
			}
			// 查询
			JobInfo job = jobService.getById(param.getId());
			// 编辑
			Date curDate = new Date();
			BeanUtils.copyProperties(param, job);
			job.setUpdateTime(curDate);
			jobService.updateById(job);
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
	public ResultJson listSearch(@RequestBody JobInfoParam param) {
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
			List<JobInfo> jobs = jobService.listSearch(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, jobs);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	
}
