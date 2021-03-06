package com.buddha.icbi.mapper.service.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.constant.OSSImageStyleConstant;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.exception.BaseException;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoTplMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.company.CompanyInfoTpl;
import com.buddha.icbi.pojo.company.FileList;
import com.buddha.icbi.pojo.member.MemberInfo;

import lombok.extern.log4j.Log4j2;


 /**
 * 
 * 公司信息-服务实现类
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
 * @时间 2018-12-10
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Service
@Log4j2
public class CompanyInfoService extends ServiceImpl<CompanyInfoMapper, CompanyInfo> {
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	@Autowired
	private CompanyInfoTplMapper companyTplMapper;
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal(3.3333).compareTo(new BigDecimal(3.3333)));
	}
	/**
	 * 附近会员
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> listNearCompany(CompanyInfoParam param) {
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		Integer id = 0;
		// 根据缩放比例控制查询数量
		if(StringUtils.isNull(param.getKeyword())) {
			if(param.getScale().compareTo(new BigDecimal(13)) == 1) {
				param.setPageSize(25);
			}
			if(param.getScale().compareTo(new BigDecimal(13)) == -1 && param.getScale().compareTo(new BigDecimal(10)) == 1) {
				param.setPageSize(50);
			}
			if(param.getScale().compareTo(new BigDecimal(10)) == -1 && param.getScale().compareTo(new BigDecimal(4)) == 1) {
				param.setPageSize(80);
			}
			if(param.getScale().compareTo(new BigDecimal(4)) == 0) {
				param.setPageSize(200);
			}
		}
		// 附近五公里
		List<CompanyInfo> companys = companyMapper.nearCompany(
				param.getLatitude(), param.getLongitude(), 
				param.getDistance(), AuditEnum.AUDITED.getValue(),
				param.getKeyword(),param.getPageSize());
		// 当前会员
		MemberInfo _member = memberMapper.selectById(param.getMemberId());
		if(null == _member) {
			log.info("当前会员不存在");
			//throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"当前会员不存在");
		}
		// 附近会员
		if(StringUtils.isNotNull(companys)) {
			for (CompanyInfo company : companys) {
				MemberLocationDto dto = new MemberLocationDto();
				dto.setAddress(company.getAddress());
				dto.setHeight(32);
				dto.setWidth(32);
				// 真实头像
				if(StringUtils.isNull(company.getRealAvatar())) {
					MemberInfo member = memberMapper.selectById(company.getMemberId());
					dto.setIconPath(member.getAvatar() + OSSImageStyleConstant.IMAGE_CIRCLE);
				}else {
					dto.setIconPath(company.getRealAvatar() + OSSImageStyleConstant.IMAGE_CIRCLE);
				}
				dto.setCompanyId(company.getId());
				dto.setMemberId(company.getMemberId());
				dto.setId(id);
				id ++;
				dto.setLongitude(company.getLongitude());
				dto.setLatitude(company.getLatitude());
				dto.setName(company.getCompanyName());
				dto.setDistance(company.getDistance()); // 距离单位公里
				dto.setRealName(company.getRealName());
				dto.setRealAvatar(company.getRealAvatar());
				dto.setMobile(company.getMobile());
				dto.setLandlineNumber(company.getLandlineNumber());
				dto.setFirstName(company.getFirstName());
				dto.setLastName(company.getLastName());
				dto.setGender(company.getGender());
				dtoList.add(dto);
			}
		}else {
			
		}
		// 放置list
		return dtoList;
	}
	
	/**
	 * 查询附近会员
	 * @param param
	 * @return
	 */
	/*public List<MemberLocationDto> listSearchCompany(CompanyInfoParam param) {
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		Integer id = 0;
		// 附近五公里
		List<CompanyInfo> companys = companyMapper.nearCompany(param.getLatitude(), param.getLongitude(), 
				new BigDecimal(10), AuditEnum.AUDITED.getValue(),param.getKeyword());
		// 当前会员
		MemberInfo _member = memberMapper.selectById(param.getMemberId());
		MemberLocationDto _dto = new MemberLocationDto();
		_dto.setAddress("公司地址");
		_dto.setHeight(32);
		_dto.setWidth(22);
		_dto.setIconPath("/images/map/marker.png");
		_dto.setMemberId(_member.getId());
		_dto.setId(id);
		id ++;
		_dto.setLongitude(param.getLongitude());
		_dto.setLatitude(param.getLatitude());
		_dto.setName("公司名称");
		_dto.setDistance(BigDecimal.ZERO); // 距离单位公里
		dtoList.add(_dto);
		// 附近会员
		if(StringUtils.isNotNull(companys)) {
			for (CompanyInfo company : companys) {
				MemberLocationDto dto = new MemberLocationDto();
				dto.setAddress(company.getAddress());
				dto.setHeight(32);
				dto.setWidth(32);
				// 查询
				dto.setIconPath(company.getRealAvatar() + OSSImageStyleConstant.IMAGE_CIRCLE);
				dto.setCompanyId(company.getId());
				dto.setMemberId(company.getMemberId());
				dto.setId(id);
				id ++;
				dto.setLongitude(company.getLongitude());
				dto.setLatitude(company.getLatitude());
				dto.setName(company.getCompanyName());
				dto.setDistance(company.getDistance()); // 距离单位公里
				dto.setRealName(company.getRealName());
				dto.setRealAvatar(company.getRealAvatar());
				dto.setMobile(company.getMobile());
				dtoList.add(dto);
			}
		}else {
			
		}
		// 放置list
		return dtoList;
	}*/
	
	
	/**
	 * 根据id返回公司信息
	 * @param param
	 * @return
	 */
	public CompanyInfo detailCompany(CompanyInfoParam param) {
		// 条件
		CompanyInfo company = this.getById(param.getId());
		if(null == company) {
			log.info("公司信息为空");
			throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"公司信息为空");
		} 
		
		// 产品标签
		if(StringUtils.isNotNull(company.getCompanyTag())) {
			company.setCompanyTag(StringUtils.vertical2comma(company.getCompanyTag()));
		}
		
		// 真实头像
		if(StringUtils.isNotNull(company.getRealAvatar())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getRealAvatar());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setRealAvatarArr(fileList);
		}
		
		// 身份证 正面
		if(StringUtils.isNotNull(company.getIdentityFront())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getIdentityFront());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setIdentityFrontArr(fileList);
		}
		
		// 身份证反面
		if(StringUtils.isNotNull(company.getIdentityBack())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getIdentityBack());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setIdentityBackArr(fileList);
		}
		
		// 公司产品
		if(StringUtils.isNotNull(company.getCompanyProduct())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyProduct());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyProductArr(fileList);
		}
		// 营业执照
		if(StringUtils.isNotNull(company.getCompanyLicense())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyLicense());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyLicenseArr(fileList);
		}
		
		// 公司logo
		if(StringUtils.isNotNull(company.getCompanyLogo())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyLogo());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyLogoArr(fileList);
		}
		
		// 公司环境
		if(StringUtils.isNotNull(company.getCompanyEnvImg())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyEnvImg());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyEnvImgArr(fileList);
		}
		return company;
	}
	
	/**
	 * 根据会员id返回公司信息
	 * @param param
	 * @return
	 */
	public CompanyInfo detailCompanyByMid(CompanyInfoParam param) {
		// 条件
		QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
		queryWrapper.getEntity().setMemberId(param.getMemberId());
		CompanyInfo company = this.getOne(queryWrapper);
		// 会员对象
		if(null == company) {
			MemberInfo member =  memberMapper.selectById(param.getMemberId());
			log.info("公司信息为空");
			//throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"公司信息为空");
			// 初始化公司信息
			Date curDate = new Date();
			CompanyInfoTpl tpl = companyTplMapper.selectById("e5adbd470f76f2b8e73e4729cd2e0dae");
			CompanyInfo entity = new CompanyInfo();
			BeanUtils.copyProperties(tpl, entity, "id");
			entity.setMemberId(param.getMemberId());
			entity.setGender(member.getGender());
			entity.setCreateTime(curDate);
			entity.setUpdateTime(curDate);
			companyMapper.insert(entity);
			log.info(">>>>>>>>>>\n",JSON.toJSONString(entity, true));
			return entity;
		}
		// 产品标签
		if(StringUtils.isNotNull(company.getCompanyTag())) {
			company.setCompanyTag(StringUtils.vertical2comma(company.getCompanyTag()));
		}
		
		// 真实头像
		if(StringUtils.isNotNull(company.getRealAvatar())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getRealAvatar());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setRealAvatarArr(fileList);
		}
		
		// 身份证 正面
		if(StringUtils.isNotNull(company.getIdentityFront())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getIdentityFront());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setIdentityFrontArr(fileList);
		}
		
		// 身份证反面
		if(StringUtils.isNotNull(company.getIdentityBack())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getIdentityBack());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setIdentityBackArr(fileList);
		}
		
		// 公司产品
		if(StringUtils.isNotNull(company.getCompanyProduct())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyProduct());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyProductArr(fileList);
		}
		// 营业执照
		if(StringUtils.isNotNull(company.getCompanyLicense())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyLicense());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyLicenseArr(fileList);
		}
		
		// 公司logo
		if(StringUtils.isNotNull(company.getCompanyLogo())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyLogo());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyLogoArr(fileList);
		}
		
		// 公司环境
		if(StringUtils.isNotNull(company.getCompanyEnvImg())) {
			List<FileList> fileList = new ArrayList<FileList>();
			String str[] = StringUtils.string2List(company.getCompanyEnvImg());
			for (int i = 0; i < str.length; i++) {
				FileList file = new FileList();
				file.setUrl(str[i]);
				file.setOssFileUrl(str[i]);
				file.setStatus("done");
				file.setUid(i);
				fileList.add(file);
			}
			company.setCompanyEnvImgArr(fileList);
		}
		return company;
	}
	
	/**
	 * 通过关键字获取对应id数组
	 * @param keyword
	 * @return
	 */
	public String[] getIdArr(String keyword) {
		String[] idArr = companyMapper.getIdArr(keyword);
		return idArr;
	}
	
	/**
	 * 根据会员id获取公司信息
	 * @param memberId
	 * @return
	 */
	public CompanyInfo getCompanyInfoByMid(String memberId) {
		QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<CompanyInfo>(new CompanyInfo());
		queryWrapper.getEntity().setMemberId(memberId);
		return companyMapper.selectOne(queryWrapper);
	}
	/**
	 * 分页查询附近会员
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> pageCompany(CompanyInfoParam param) {
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		Integer id = 0;
		// 附近五公里
		List<CompanyInfo> companys = companyMapper.pageCompany(
				param.getLatitude(), param.getLongitude(), 
				param.getDistance(), AuditEnum.AUDITED.getValue(),
				param.getKeyword(),param.getPage(),param.getPageSize());
		System.out.println(JSON.toJSONString(companys, true));
		// 当前会员
		MemberInfo _member = memberMapper.selectById(param.getMemberId());
		if(null == _member) {
			log.info("当前会员不存在");
			//throw new BaseException(ResultStatusEnum.DATA_NOT_EXIST,"当前会员不存在");
		}
		// 附近会员
		if(StringUtils.isNotNull(companys)) {
			for (CompanyInfo company : companys) {
				MemberLocationDto dto = new MemberLocationDto();
				dto.setAddress(company.getAddress());
				dto.setHeight(32);
				dto.setWidth(32);
				// 真实头像
				if(StringUtils.isNull(company.getRealAvatar())) {
					MemberInfo member = memberMapper.selectById(company.getMemberId());
					dto.setIconPath(member.getAvatar() + OSSImageStyleConstant.IMAGE_CIRCLE);
				}else {
					dto.setIconPath(company.getRealAvatar() + OSSImageStyleConstant.IMAGE_CIRCLE);
				}
				dto.setCompanyId(company.getId());
				dto.setMemberId(company.getMemberId());
				dto.setId(id);
				id ++;
				dto.setLongitude(company.getLongitude());
				dto.setLatitude(company.getLatitude());
				dto.setName(company.getCompanyName());
				dto.setDistance(company.getDistance()); // 距离单位公里
				dto.setRealName(company.getRealName());
				dto.setRealAvatar(company.getRealAvatar());
				dto.setMobile(company.getMobile());
				dto.setLandlineNumber(company.getLandlineNumber());
				dto.setFirstName(company.getFirstName());
				dto.setLastName(company.getLastName());
				dto.setGender(company.getGender());
				dtoList.add(dto);
			}
		}else {
			
		}
		// 放置list
		return dtoList;
	}
}
