package com.buddha.icbi.common.param.demand;

import java.math.BigDecimal;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * 需求采购-参数
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网科技设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Getter
@Setter
public class DemandInfoParam extends BaseParam {
	
	/**
	 * 公司名称
	 */
	private String companyName;
    /**
     * 联系电话
     */
	private String mobile;
    /**
     * 产品名称
     */
	private String productName;
	/**
	 * 产品图片
	 */
	private String productImg;
    /**
     * 规格
     */
	private String norm;
    /**
     * 数量
     */
	private Integer amount;
    /**
     * 收货坐标
     */
	private BigDecimal receiveLatitude;
    /**
     * 收货坐标
     */
	private BigDecimal receiveLongitude;
    /**
     * 收货地址
     */
	private String receiveAddress;
    /**
     * 当前位置坐标
     */
	private BigDecimal currentLatitude;
    /**
     * 发起人当前坐标
     */
	private BigDecimal currentLongitude;
    /**
     * 标签
     */
	private String tags;
    /**
     * 状态 1-审核中 2-通过 3-拒绝
     */
	private Integer status;
	/**
	 * 创建人id
	 */
	private String createId;
	/**
     * 纬度，范围为 -90~90，负数表示南纬
     */
	private BigDecimal latitude;
    /**
     * 经度，范围为 -180~180，负数表示西经
     */
	private BigDecimal longitude;
	/**
	 * 关键字查询
	 */
	private String keyword;
	/**
	 * 距离
	 */
	private BigDecimal distance;
	
}
