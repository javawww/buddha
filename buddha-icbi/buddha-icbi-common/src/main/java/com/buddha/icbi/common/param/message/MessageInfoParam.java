package com.buddha.icbi.common.param.message;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class MessageInfoParam extends BaseParam{

	/**
	 * 消息去向
	 */
	private String toId;
	
	/**
	 * 消息来源
	 */
	private String fromId;
	/**
	 * 消息内容
	 */
	private String message;
	/**
	 * 图片地址
	 */
	private String url;
	/**
	 * 状态 0-已发送 1-已阅读 2-为阅读
	 */
	private Integer status;
	/**
	 * 类型：1-文本 2-图片
	 */
	private Integer type;
}
