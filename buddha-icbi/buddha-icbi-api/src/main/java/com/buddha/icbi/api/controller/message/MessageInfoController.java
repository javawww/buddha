package com.buddha.icbi.api.controller.message;

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
import com.buddha.icbi.common.dto.MessageInfoDto;
import com.buddha.icbi.common.param.message.MessageInfoParam;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.mapper.service.message.MessageInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.message.MessageInfo;

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
@RequestMapping("message-info")
@Log4j2
public class MessageInfoController extends WebBaseController{

	@Autowired
	private MessageInfoService	messageService;
	
	@Autowired
	private CompanyInfoService companyService;
	/**
	 * 保存聊天消息
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveMessage(@RequestBody MessageInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getToId())) {
				log.info("toid为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"toid为空");
			}
			if(StringUtils.isNull(param.getFromId())) {
				log.info("fromid为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"fromid为空");
			}
			Date curDate = new Date();
			MessageInfo messageInfo = new MessageInfo();
			BeanUtils.copyProperties(param, messageInfo);
			messageInfo.setCreateTime(curDate);
			messageInfo.setUpdateTime(curDate);
			messageService.save(messageInfo);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 消息列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson messageList(@RequestBody MessageInfoParam param) {
		try {
			// 判断
			if(StringUtils.isNull(param.getFromId())) {
				log.info("fromid为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"fromid为空");
			}
			// 获取公司
			QueryWrapper<CompanyInfo> companyQW = super.getQueryWrapper(CompanyInfo.class);
			companyQW.getEntity().setMemberId(param.getFromId());
			CompanyInfo _company = companyService.getOne(companyQW);
			// 查询消息
			QueryWrapper<MessageInfo> queryWrapper = super.getQueryWrapper(MessageInfo.class);
			queryWrapper.getEntity().setFromId(_company.getId());
			queryWrapper.groupBy("to_id");
			queryWrapper.orderByDesc("create_time");
			List<MessageInfo> list = messageService.list(queryWrapper);
			// 封装数据
			List<MessageInfoDto> dtoList = new ArrayList<MessageInfoDto>();
			if(StringUtils.isNotNull(list)) {
				for (MessageInfo rec : list) {
					MessageInfoDto dto = new MessageInfoDto();
					CompanyInfo company = companyService.getById(rec.getToId());
					dto.setRealName(company.getRealName());
					dto.setRealAvatar(company.getRealAvatar());
					dto.setNewMsg(rec.getMessage());
					dto.setToId(company.getMemberId());
					String time = DateTimeUtils.getDateTimeFormatToString(rec.getCreateTime(), DateTimeUtils.FORMAT_MM_DD);
					dto.setTime(time);
					dtoList.add(dto);
				}
			}
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,dtoList);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
