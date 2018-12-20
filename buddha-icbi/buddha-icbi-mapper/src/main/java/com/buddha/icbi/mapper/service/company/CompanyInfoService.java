package com.buddha.icbi.mapper.service.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.constant.OSSImageStyleConstant;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.common.dto.MemberLocationDto;
import com.buddha.icbi.common.enums.AuditEnum;
import com.buddha.icbi.common.param.company.CompanyInfoParam;
import com.buddha.icbi.mapper.mapper.company.CompanyInfoMapper;
import com.buddha.icbi.mapper.mapper.member.MemberInfoMapper;
import com.buddha.icbi.pojo.company.CompanyInfo;
import com.buddha.icbi.pojo.member.MemberInfo;


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
public class CompanyInfoService extends ServiceImpl<CompanyInfoMapper, CompanyInfo> {
	
	@Autowired
	private MemberInfoMapper memberMapper;
	
	@Autowired
	private CompanyInfoMapper companyMapper;
	
	/**
	 * 附近会员
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> listNearCompany(CompanyInfoParam param) {
		// 封装对象
		List<MemberLocationDto> dtoList = new ArrayList<MemberLocationDto>();
		Integer id = 0;
		// 附近五公里
		List<CompanyInfo> companys = companyMapper.nearCompany(param.getLatitude(), param.getLongitude(), 
				new BigDecimal(10), AuditEnum.AUDITED.getValue(),null);
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
	}
	
	/**
	 * 查询附近会员
	 * @param param
	 * @return
	 */
	public List<MemberLocationDto> listSearchCompany(CompanyInfoParam param) {
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
	}
	
}
