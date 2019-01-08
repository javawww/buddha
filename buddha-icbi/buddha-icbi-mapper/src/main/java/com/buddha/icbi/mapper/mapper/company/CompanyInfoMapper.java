package com.buddha.icbi.mapper.mapper.company;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.icbi.pojo.company.CompanyInfo;


  /**
 * 
 *  公司信息 Mapper 接口
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-10
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

	/**
	 * 附近公司信息
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @param mids
	 * @return
	 */
	public List<CompanyInfo> nearByCompanyList(@Param("latitude") BigDecimal latitude,@Param("longitude") BigDecimal longitude,@Param("distance") BigDecimal distance,@Param("mids") List<String> mids);
	
	/**
	 * 附近认证的公司
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @return
	 */
	public List<CompanyInfo> nearCompany(
			@Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude,
			@Param("distance") BigDecimal distance,
			@Param("isCertification") Integer isCertification,
			@Param("keyword") String keyword,
			@Param("size") Integer size);
	
	public List<CompanyInfo> foreachTest(@Param("mids") List<String> mids);
	
	/**
	 * 通过关键字获取id数组
	 * @param keyword
	 * @return
	 */
	public String[] getIdArr(@Param("keyword") String keyword);
	
	/**
	 * 分页 附近认证公司
	 * @param latitude
	 * @param longitude
	 * @param distance
	 * @param isCertification
	 * @param keyword
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<CompanyInfo> pageCompany(
			@Param("latitude") BigDecimal latitude,
			@Param("longitude") BigDecimal longitude,
			@Param("distance") BigDecimal distance,
			@Param("isCertification") Integer isCertification,
			@Param("keyword") String keyword,
			@Param("page") Integer page, 
			@Param("pageSize") Integer pageSize);
}