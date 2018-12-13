package com.buddha.icbi.api.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.param.wechat.Code2SessionParam;
import com.buddha.component.common.util.IPUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.component.wechat.bean.Code2SessionBean;
import com.buddha.component.wechat.service.WechatService;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.bean.LoginUserInfoBean;
import com.buddha.icbi.common.dto.MemberInfoDto;
import com.buddha.icbi.common.param.member.MemberInfoParam;
import com.buddha.icbi.mapper.service.member.MemberInfoService;
import com.buddha.icbi.pojo.member.MemberInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "会员信息controller",tags = {"会员信息接口"})
@RestController
@RequestMapping("member-info")
@Log4j2
public class MemberInfoController extends WebBaseController{

	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private MemberInfoService memberService;
	
	/**
	 * 微信小程序授权登录
	 * 获取openid
	 * @return
	 */
	@ApiOperation(value="member-info/get-openid",tags={"◆获取会员openId"}, notes="每个会员都有一个小程序对应的唯一标识：openId")
	@PostMapping("get-openid")
	public ResultJson getOpenId(@RequestBody Code2SessionParam param) {
		try {
			if(StringUtils.isNull(param.getJsCode())) {
				log.info("code参数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"code参数为空");
			}
			// 获取用户openid
			Code2SessionBean bean = wechatService.getCode2SessionObj(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,bean);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 登录 通过openid获取会员信息
	 * @param param
	 * @return
	 */
	@ApiOperation(value="member-info/login",tags={"◆会员登录"}, notes="每个会员都有一个小程序对应的唯一标识：openId")
	@PostMapping("login")
	public ResultJson login(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId参数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId参数为空");
			}
			if(null == param.getLoginSource() || param.getLoginSource() == 0) {
				log.info("登录来源为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"登录来源为空");
			}
			// 登录IP地址
			String clientIp = IPUtils.getClientIP(request);
			param.setClientIp(clientIp);
			// 获取登录对象
			LoginUserInfoBean userBean = memberService.getLoginUserBean(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,userBean);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 首次注册
	 * @return
	 */
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public ResultJson register(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId参数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId参数为空");
			}
			/*if(StringUtils.isNull(param.getUnionId())) {
				log.info("unionId参数为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"unionId参数为空");
			}*/
			// 保存用户数据
			memberService.saveMemberInfo(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	/**
	 * 更新会员名片数据
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public ResultJson updateBusCard(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId为空");
			}
			// 更新用户数据
			memberService.updateMemberInfo(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 更新会员所属组织：商会、协会、母公司
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "update-organization",method = RequestMethod.POST)
	public ResultJson updateOrganization(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId为空");
			}
			
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 查看详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "detail",method = RequestMethod.POST)
	public ResultJson detailMemberInfo(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId为空");
			}
			// 会员详情数据
			MemberInfoDto memberDto = memberService.detailMemberInfo(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,memberDto);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 *  查询所有认证过的会员信息
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "list-member",method = RequestMethod.POST)
	public ResultJson listMember(@RequestBody MemberInfoParam param) {
		try {
			if(StringUtils.isNull(param.getOpenId())) {
				log.info("openId为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"openId为空");
			}
			// 会员列表
			List<MemberInfo> members = memberService.listMember(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS,members);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}