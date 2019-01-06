package com.buddha.icbi.common.param.activity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.buddha.component.common.param.base.BaseParam;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * //在这里注释类文件的作用等信息
 *
 * <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 佛学禅语： <br/>
 * 卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 * 
 * 
 * @作者 chuck
 * @时间 2018年12月8日
 * @版权 深圳市佛系派互联网科技集团
 */
@Getter
@Setter
public class ActivityInfoParam extends BaseParam {

	/**
	 * 创建人id
	 */
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Phoenix")
	private Date holdTime;
	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Phoenix")
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
	 * 地址详情
	 */
	private String addressDetail;
	/**
	 * 封面图片
	 */
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
	 * 举办状态：1-未开始 2-进行中 3-已结束
	 */
	private Integer holdStatus;
	/**
	 * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
	 */
	private Integer chargeType;
	/**
	 * 收费类型： 1-免费 2-女免费男AA 3-女半价男AA
	 */
	private String chargeDesc;
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
	/**
	 * 关键字查询
	 */
	private String keyword;
	/**
	 * 距离
	 */
	private BigDecimal distance;
}
