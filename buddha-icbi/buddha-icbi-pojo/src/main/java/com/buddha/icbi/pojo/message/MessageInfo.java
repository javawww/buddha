package com.buddha.icbi.pojo.message;

import java.io.Serializable;
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
 * 聊天消息信息-数据库实体对象
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
@TableName("message_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageInfo extends PojoModel<MessageInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 消息去向
	 */
	@TableField("to_id")
	private String toId;
	
	/**
	 * 消息来源
	 */
	@TableField("from_id")
	private String fromId;
	/**
	 * 消息内容
	 */
	@TableField("message")
	private String message;
	/**
	 * 图片地址
	 */
	@TableField("url")
	private String url;
	/**
	 * 状态 0-已发送 1-已阅读 2-为阅读
	 */
	@TableField("status")
	private Integer status;
	/**
	 * 类型：1-文本 2-图片
	 */
	@TableField("type")
	private Integer type;
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
	
	// ##################非数据库属性######################
	/**
	 * 会员id
	 */
	@TableField(exist = false)
	private String memberId;
	/**
	 * 显示时间
	 */
	@TableField(exist = false)
	private String showTime;
}
