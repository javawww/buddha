package com.buddha.icbi.mapper.service.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.util.DateTimeUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.dto.MessageInfoDto;
import com.buddha.icbi.common.param.message.MessageInfoParam;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.mapper.mapper.message.MessageInfoMapper;
import com.buddha.icbi.mapper.service.company.CompanyInfoService;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberInfo;
import com.buddha.icbi.pojo.message.MessageInfo;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * 聊天消息 服务实现类
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
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Service
@Log4j2
public class MessageInfoService extends ServiceImpl<MessageInfoMapper, MessageInfo> {
	
	@Autowired
	private MessageInfoMapper messageMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	/**
	 * 最近消息列表
	 * @param param
	 * @return
	 */
	public List<MessageInfoDto> recentList(MessageInfoParam param) {
		List<MessageInfoDto> dtoList = new ArrayList<MessageInfoDto>();
		List<MessageInfo> list = messageMapper.recentList(param.getMemberId());
		if(StringUtils.isNotNull(list)) {
			for (MessageInfo rec : list) {
				MessageInfoDto dto = new MessageInfoDto();
				// 获取公司信息
				QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
				queryWrapper.getEntity().setMemberId(rec.getMemberId());
				CompanyInfo company = companyMapper.selectOne(queryWrapper);
				if(null != company) {
					if(StringUtils.isNull(company.getRealName())) {
						MemberInfo member = memberMapper.selectById(company.getMemberId());
						dto.setRealAvatar(member.getAvatar());
					}else {
						dto.setRealName(company.getRealName());
					}
					if(StringUtils.isNull(company.getRealAvatar())) {
						MemberInfo member = memberMapper.selectById(company.getMemberId());
						dto.setRealName(member.getNickName());
					}else {
						dto.setRealAvatar(company.getRealAvatar());
					}
				}else {
					log.info("公司信息为空-查询会员信息");
					MemberInfo member = memberMapper.selectById(rec.getMemberId());
					dto.setRealName(member.getNickName());
					dto.setRealAvatar(member.getAvatar());
				}
				dto.setToId(rec.getMemberId());
				dto.setNewMsg(rec.getMessage());
				String time = DateTimeUtils.getDateTimeFormatToString(rec.getCreateTime(), DateTimeUtils.FORMAT_MM_DD_HH_MM);
				dto.setTime(time);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 聊天记录
	 * @param param
	 * @return
	 */
	public List<MessageInfo> chatList(MessageInfoParam param) {
		// 查询
		List<MessageInfo> msgList = messageMapper.chatList(param.getFromId(),param.getToId());
		if(StringUtils.isNotEmpty(msgList)) {
			for (MessageInfo msg : msgList) {
				msg.setShowTime(DateTimeUtils.getDateTimeFormatToString(msg.getCreateTime(), DateTimeUtils.FORMAT_YYYY_MM_CHINA));
			}
		}else {
			log.info("数据不存在");
		}
		return msgList;
	}

}
