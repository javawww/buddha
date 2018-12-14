package com.buddha.icbi.common.param.activity;

import java.math.BigDecimal;

import com.buddha.component.common.param.base.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * //在这里注释类文件的作用等信息
 *
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *佛学禅语：
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *  
 * 
 * @作者   	chuck
 * @时间 	2018年12月8日
 * @版权  	深圳市佛系派互联网科技集团
 */
@Getter
@Setter
public class ActivityInfoParam extends BaseParam{
	
	/**
	 * 会员id
	 */
	private String memberId;
	 /**
     * 活动主题
     */
	private String theme;
    /**
     * 参与数量
     */
	private Integer amount;
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
     * 活动具体地点
     */
	private String place;
    /**
     * 活动海报 多张海报用|分割
     */
	private String post;
    /**
     *  状态： 1-报名中 2-进行中 3-已结束
     */
	private Integer status;
    /**
     * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
     */
	private Integer chargeType;
    /**
     * 活动内容介绍
     */
	private String content;
    /**
     * 是否取消 1-正常 2-取消
     */
	private Integer isCancel;
    /**
     * 取消原因
     */
	private String cancelExplain;
    /**
     * 是否删除 1-正常 2-删除
     */
	private Integer isDel;

}
