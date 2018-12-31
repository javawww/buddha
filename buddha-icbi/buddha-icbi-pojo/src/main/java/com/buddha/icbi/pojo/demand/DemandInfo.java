package com.buddha.icbi.pojo.demand;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.common.bean.mybatis.PojoModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
 /**
 * 
 * 需求咨询-数据库实体对象
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
 * @时间 2018-12-31
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@TableName("demand_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class DemandInfo extends PojoModel<DemandInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 单位名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 联系电话
     */
	private String mobile;
    /**
     * 产品名称
     */
	@TableField("product_name")
	private String productName;
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
	@TableField("receive_latitude")
	private BigDecimal receiveLatitude;
    /**
     * 收货坐标
     */
	@TableField("receive_longitude")
	private BigDecimal receiveLongitude;
    /**
     * 收货地址
     */
	@TableField("receive_address")
	private String receiveAddress;
    /**
     * 当前位置坐标
     */
	@TableField("current_latitude")
	private BigDecimal currentLatitude;
    /**
     * 发起人当前坐标
     */
	@TableField("current_longitude")
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
	@TableField("create_id")
	private String createId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
