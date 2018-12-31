package com.buddha.icbi.common.param.job;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 招聘-参数
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
public class JobInfoParam extends BaseParam{

	
	/**
     * 招聘标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 岗位
     */
	private String jobDesc;
    /**
     * 待遇
     */
	private String salaryDesc;
    /**
     * 状态 1-审核中 2-通过 3-拒绝
     */
	private Integer status;
    /**
     * 创建人id
     */
	private String createId;
}
