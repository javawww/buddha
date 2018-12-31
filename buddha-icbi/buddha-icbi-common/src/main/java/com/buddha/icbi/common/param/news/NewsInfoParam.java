package com.buddha.icbi.common.param.news;

import java.math.BigDecimal;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 风采咨询-参数
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
public class NewsInfoParam extends BaseParam{

	/**
	 * 状态 0-审核中 1-审核通过 2-审核拒绝
	 */
	private Integer status;
    /**
     * 类型 1-商会风采 2-公司风采 3-个人风采
     */
	private Integer type;
    /**
     * 纬度，范围为 -90~90，负数表示南纬
     */
	private BigDecimal latitude;
    /**
     * 经度，范围为 -180~180，负数表示西经
     */
	private BigDecimal longitude;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 封面图片
     */
	private String coverImg;
    /**
     * 创建人即会员id
     */
	private String createId;
	/**
	 * 关键字查询
	 */
	private String keyword;
	/**
	 * 距离
	 */
	private BigDecimal distance;
	
}
