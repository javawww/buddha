package com.buddha.icbi.pojo.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buddha.component.common.bean.mybatis.PojoModel;
import com.buddha.icbi.pojo.company.FileList;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 线下活动信息-数据库实体对象
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
@TableName("activity_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityInfo extends PojoModel<ActivityInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 会员id
	 */
	@TableField("create_id")
	private String createId;
	/**
	 * 活动主题
	 */
	private String theme;
	/**
	 * 参与数量
	 */
	private Integer amount;
	/**
	 * 举办时间
	 */
	@TableField("hold_time")
	private Date holdTime;
	/**
	 * 结束时间
	 */
	@TableField("over_time")
	private Date overTime;
	/**
	 * 活动代号
	 */
	private String code;
	/**
	 * 纬度
	 */
	private BigDecimal latitude;
	/**
	 * 经度
	 */
	private BigDecimal longitude;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 详细地址
	 */
	@TableField("address_detail")
	private String addressDetail;
	/**
	 * 封面图片
	 */
	@TableField("cover_img")
	private String coverImg;
	/**
	 * 活动海报 多张海报用|分割
	 */
	private String post;
	/**
	 * 状态： 0-审核中 1-通过 2-拒绝
	 */
	private Integer status;
	/**
	 * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
	 */
	@TableField("charge_type")
	private Integer chargeType;
	/**
	 * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
	 */
	@TableField("charge_desc")
	private String chargeDesc;
	/**
	 * 活动内容介绍
	 */
	private String content;
	/**
	 * 是否取消 1-正常 2-取消
	 */
	@TableField("is_cancel")
	private Integer isCancel;
	/**
	 * 取消原因
	 */
	@TableField("cancel_explain")
	private String cancelExplain;
	/**
	 * 是否删除 1-正常 2-删除
	 */
	@TableField("is_del")
	private Integer isDel;
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

	// ###############额外属性##########################
	/**
	 * 封面图片
	 */
	@TableField(exist = false)
	private List<FileList> coverImgArr;
	/**
	 * 距离
	 */
	@TableField(exist = false)
	private BigDecimal distance;
	/**
	 * 真实头像
	 */
	@TableField(exist = false)
	private String realAvatar;
	/**
	 * 公司id
	 */
	@TableField(exist = false)
	private String companyId;
	/**
	 * 公司名称
	 */
	@TableField(exist = false)
	private String companyName;
	/**
	 * 公司简介
	 */
	@TableField(exist = false)
	private String companyProfile;
	
	//举办时间
	@TableField(exist = false)
	private String hodeTimetxt;
	// 结束时间
	@TableField(exist = false)
	private String overTimetxt;
}
