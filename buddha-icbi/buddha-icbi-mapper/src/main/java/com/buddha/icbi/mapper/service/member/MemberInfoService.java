package com.buddha.icbi.mapper.service.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.exception.BaseException;
import com.buddha.component.common.util.EmojiUtils;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.bean.LoginUserInfoBean;
import com.buddha.icbi.common.dto.MemberInfoDto;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.common.param.member.MemberInfoParam;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.mapper.mapper.member.MemberOrganizationMapper;
import com.buddha.icbi.mapper.mapper.product.ProductImageMapper;
import com.buddha.icbi.mapper.mapper.product.ProductTagMapper;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberInfo;
import com.buddha.icbi.pojo.member.MemberOrganization;
import com.buddha.icbi.pojo.product.ProductImage;
import com.buddha.icbi.pojo.product.ProductTag;

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
	private ProductImageMapper imageMapper;
	
	@Autowired
	private ProductTagMapper tagMapper;
	
	@Autowired
	private MemberOrganizationMapper organizationMapper;
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	/**
	 * 根据用户openId获取对应用户数据
	 * @param param
	 * @return
	 */
	public LoginUserInfoBean getLoginUserBean(MemberInfoParam param) {
		Date curDate = new Date();
		// 查询会员信息
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		log.info("queryWrapper value is ===>>"+queryWrapper);
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
	}
	
	/**
	 * 更新用户信息
	 * @param param
	 */
	public void updateMemberInfo(MemberInfoParam param) {
		Date curDate = new Date();
		String openId = param.getOpenId();
		// 获取用户对象
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		queryWrapper.getEntity().setOpenId(openId);
		MemberInfo member = this.getOne(queryWrapper);
		if(null == member) {
			log.info("用户信息不存在");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"用户信息不存在");
		}
		String memberId = member.getId();
		// 更新用户对象
		BeanUtils.copyProperties(param, member);
		member.setUpdateTime(curDate);
		this.update(member, queryWrapper);
		// 公司信息
		if(StringUtils.isNotEmpty(param.getCompanyParam())) {
			CompanyInfoParam companyParam = param.getCompanyParam();
			CompanyInfo companyInfo = new CompanyInfo();
			BeanUtils.copyProperties(companyParam,companyInfo);
			companyInfo.setMemberId(memberId);
			companyInfo.setCreateTime(curDate);
			companyInfo.setUpdateTime(curDate);
			companyMapper.insert(companyInfo);
		}
		// 产品图片
		List<String>  proImgArr = param.getProductImages();
		if(StringUtils.isNotNull(proImgArr)) {
			// 先清空
			QueryWrapper<ProductImage> imageWrapper = new QueryWrapper<ProductImage>(new ProductImage());
			imageWrapper.getEntity().setMemberId(memberId);
			imageMapper.delete(imageWrapper);
			// 再新增
			for (String rec : proImgArr) {
				ProductImage entity = new ProductImage();
				entity.setImgUrl(rec);
				entity.setMemberId(memberId);
				entity.setCreateTime(curDate);
				entity.setUpdateTime(curDate);
				imageMapper.insert(entity);
			}
		}
		// 行业标签
		if(StringUtils.isNotNull(param.getProductTags())) {
			// 先清空
			QueryWrapper<ProductTag> tagWrapper = new QueryWrapper<ProductTag>(new ProductTag());
			tagWrapper.getEntity().setMemberId(memberId);
			tagMapper.delete(tagWrapper);
			// 再新增
			String proTags = StringUtils.replace2Str(param.getProductTags());
			String[] proTagArr = StringUtils.string2List(proTags);
			for (String rec : proTagArr) {
				ProductTag entity = new ProductTag();
				entity.setTagName(rec);
				entity.setMemberId(memberId);
				entity.setCreateTime(curDate);
				entity.setUpdateTime(curDate);
				tagMapper.insert(entity);
			}
		}
	}
	
	/**
	 * 获取会员详情数据
	 * @param param
	 * @return
	 */
	public MemberInfoDto detailMemberInfo(MemberInfoParam param) {
		String openId = param.getOpenId();
		String memberId = param.getId();
		MemberInfoDto memberDto = new MemberInfoDto();
		// 获取用户对象
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		queryWrapper.getEntity().setOpenId(openId);
		MemberInfo member = this.getOne(queryWrapper);
		if(null == member) {
			log.info("用户信息不存在");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"用户信息不存在");
		}
		memberDto.setMemberInfo(member);
		// 获取公司信息
		QueryWrapper<CompanyInfo> companyWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
		companyWrapper.getEntity().setMemberId(memberId);
		CompanyInfo companyInfo = companyMapper.selectOne(companyWrapper);
		if(StringUtils.isNotEmpty(companyInfo)) {
			memberDto.setCompanyInfo(companyInfo);
		}
		// 获取会员产品信息
		List<String> proImageArr = imageMapper.selectImgUrl(memberId);
		if(StringUtils.isNotNull(proImageArr)) {
			memberDto.setProImageArr(proImageArr);
		}
		// 获取行业标签信息
		QueryWrapper<ProductTag> tagWrapper = new QueryWrapper<ProductTag>(new ProductTag());
		tagWrapper.getEntity().setMemberId(memberId);
		List<ProductTag> proTagArr = tagMapper.selectList(tagWrapper);
		List<String> tagStrArr = tagMapper.selectTagName(memberId);
		if(StringUtils.isNotNull(proTagArr)) {
			memberDto.setProTagArr(tagStrArr);
			String productTags = StringUtils.list2String(tagStrArr);
			memberDto.setProductTags(productTags);
		}
		// 获取组织数据
		QueryWrapper<MemberOrganization> organizationWrapper = new QueryWrapper<MemberOrganization>(new MemberOrganization());
		organizationWrapper.getEntity().setMemberId(member.getId());
		List<MemberOrganization> organizationArr = organizationMapper.selectList(organizationWrapper);
		if(StringUtils.isNotNull(organizationArr)) {
			memberDto.setOrganizationArr(organizationArr);
		}
		return memberDto;
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
	/**
	 * 附近会员
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> listMemberLocation(MemberInfoParam param) {
		// 查询
		QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<MemberInfo>(new MemberInfo());
		List<MemberInfo> list = memberMapper.selectList(queryWrapper);
		if(null == list || list.size() == 0) {
			log.info("数据库暂无用户");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"数据库暂无用户");
		}
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		for (MemberInfo member : list) {
			// 获取公司信息
			QueryWrapper<CompanyInfo> companyQuery = new QueryWrapper<CompanyInfo>(new CompanyInfo());
			companyQuery.getEntity().setMemberId(member.getId());
			CompanyInfo company  = companyMapper.selectOne(companyQuery);
			if(null == company) {
				log.info("公司信息为空");
				throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"公司信息为空");
			}
			MemberLocationDto dto = new MemberLocationDto();
			dto.setAddress(company.getAddress());
			dto.setHeight(32);
			dto.setWidth(32);
			dto.setIconPath(member.getRealAvatar());
			dto.setId(member.getId());
			// 
			dto.setLongitude(company.getLongitude());
			dto.setLatitude(company.getLatitude());
			//
			dto.setName(company.getCompanyName());
			// 放置list
			dtoList.add(dto);
		}
		return dtoList;
	}
	
}
