package com.buddha.icbi.pojo.job;

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
 * -数据库实体对象
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
@TableName("job_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class JobInfo extends PojoModel<JobInfo> {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 招聘标题
     */
	private String title;
	/**
	 * 纬度，范围为 -90~90，负数表示南纬
	 */
	private BigDecimal latitude;
	/**
	 * 经度，范围为 -180~180，负数表示西经
	 */
	private BigDecimal longitude;
	/**
	 * 地图位置
	 */
	private String address;
	/**
	 * 详细地址
	 */
	@TableField("address_detail")
	private String addressDetail;
    /**
     * 内容
     */
	private String content;
	/**
	 * 封面图片
	 */
	@TableField("cover_img")
	private String coverImg;
    /**
     * 岗位
     */
	@TableField("job_desc")
	private String jobDesc;
    /**
     * 待遇
     */
	@TableField("salary_desc")
	private String salaryDesc;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
     * 联系电话
     */
	private String mobile;
	/**
	 * 联系人
	 */
	@TableField("contact_name")
	private String contactName;
    /**
     * 状态 0-审核中 1-通过 2-拒绝
     */
	private Integer status;
	/**
	 * 是否启用 0-启用 1-禁用
	 */
	private Integer enable;
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
//###############额外属性##########################
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
}
