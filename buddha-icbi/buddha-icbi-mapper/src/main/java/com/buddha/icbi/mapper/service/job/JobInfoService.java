package com.buddha.icbi.mapper.service.job;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.job.JobInfoParam;
import com.buddha.icbi.mapper.mapper.job.JobInfoMapper;
import com.buddha.icbi.pojo.job.JobInfo;


 /**
 * 
 *  服务实现类
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
 * @时间 2018-12-31
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Service
public class JobInfoService extends ServiceImpl<JobInfoMapper, JobInfo> {
	
	@Autowired
	private JobInfoMapper jobMapper;
	
	/**
	 * 查询附近列表
	 * @param param
	 * @return
	 */
	public List<JobInfo> listSearch(JobInfoParam param) {
		// 查询
		param.setStatus(AuditEnum.AUDITED.getValue()); // 审核通过
		param.setDistance(new BigDecimal(10000));// 距离
		List<JobInfo> jobs = jobMapper.listSearch(param);
		return jobs;
	}
	
}
