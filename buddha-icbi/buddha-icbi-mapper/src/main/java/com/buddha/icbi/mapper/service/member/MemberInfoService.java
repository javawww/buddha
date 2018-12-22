package com.buddha.icbi.mapper.service.member;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.exception.BaseException;
import com.buddha.component.common.util.EmojiUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.bean.LoginUserInfoBean;
import com.buddha.icbi.common.param.member.MemberInfoParam;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoTplMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.CompanyInfoTpl;
import com.buddha.icbi.pojo.member.MemberInfo;

import lombok.extern.log4j.Log4j2;


 /**
 * 
 * 会员基本信息 服务实现类
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
public class MemberInfoService extends ServiceImpl<MemberInfoMapper, MemberInfo> {
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	@Autowired
	private CompanyInfoTplMapper companyTplMapper;
	/**
	 * 根据用户openId获取对应用户数据
	 * @param param
	 * @return
	 */
	public LoginUserInfoBean getLoginUserBean(MemberInfoParam param) {
		Date curDate = new Date();
		// 查询会员信息
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		queryWrapper.getEntity().setOpenId(param.getOpenId());
		MemberInfo member = this.getOne(queryWrapper);
		if(null == member) {
			log.info("会员数据不存在");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"会员数据不存在");
		}
		// 封装登录对象并返回
		LoginUserInfoBean userBean = new LoginUserInfoBean();
		userBean.setLoginSource(param.getLoginSource());
		userBean.setMemberInfo(member);
		// 更新登录信息
		member.setLastLoginTime(curDate);
		member.setLastLoginIp(param.getClientIp());
		this.updateById(member);
		
		return userBean;
	}

	/**
	 * 保存新用户信息
	 * @param param
	 */
	public void saveMemberInfo(MemberInfoParam param) {
		Date curDate = new Date();
		// 判断是否已存在用户信息
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		queryWrapper.getEntity().setOpenId(param.getOpenId());
		MemberInfo qMember = this.getOne(queryWrapper);
		if(null != qMember) {
			log.info("会员数据已经存在");
			throw new BaseException(ResultStatusEnum.DATA_IS_EXIST,"会员数据已经存在");
		}
		// 微信昵称特殊字符处理
		if(!StringUtils.isEmpty(param.getNickName())) {
			String nickName = EmojiUtils.filterEmoji(param.getNickName());
			param.setNickName(nickName);
		}
		// 赋值数据
		MemberInfo member = new MemberInfo();
		BeanUtils.copyProperties(param, member);;
		member.setCreateTime(curDate);
		member.setUpdateTime(curDate);
		this.save(member);
		// 初始化公司信息
		CompanyInfoTpl tpl = companyTplMapper.selectById("e5adbd470f76f2b8e73e4729cd2e0dae");
		CompanyInfo entity = new CompanyInfo();
		BeanUtils.copyProperties(tpl, entity);
		entity.setMemberId(member.getId());
		entity.setCreateTime(curDate);
		entity.setUpdateTime(curDate);
		companyMapper.insert(entity);
	}
	
	/**
	 * 获取会员详情数据
	 * @param param
	 * @return
	 */
	public MemberInfo detailMemberInfo(MemberInfoParam param) {
		// 获取用户对象
		MemberInfo member = super.getById(param.getId());
		if(null == member) {
			log.info("用户信息不存在");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"用户信息不存在");
		}
		return member;
	}
	
	/**
	 * 查询会员列表：已认证
	 * @param param
	 * @return
	 */
	public List<MemberInfo> listMember(MemberInfoParam param) {
		// 查询
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		List<MemberInfo> list = memberMapper.selectList(queryWrapper);
		if(null == list || list.size() == 0) {
			log.info("数据库暂无用户");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"数据库暂无用户");
		}
		return list;
	}
	
}
