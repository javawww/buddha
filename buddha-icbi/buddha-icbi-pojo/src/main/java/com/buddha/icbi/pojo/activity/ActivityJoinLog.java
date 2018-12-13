package com.buddha.icbi.pojo.activity;

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
 * 活动加入日志记录-数据库实体对象
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
@TableName("activity_join_log")
@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityJoinLog extends PojoModel<ActivityJoinLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 活动id
     */
	@TableField("activity_id")
	private String activityId;
    /**
     * 活动发起人id
     */
	@TableField("initiate_id")
	private String initiateId;
    /**
     * 参与会员id
     */
	@TableField("member_id")
	private String memberId;
    /**
     * 会员手机号
     */
	@TableField("member_mobile")
	private String memberMobile;
    /**
     * 会员真实姓名
     */
	@TableField("member_real_name")
	private String memberRealName;
    /**
     * 状态 1-正常 2-自动退出 3-强制退出
     */
	private Integer status;
    /**
     * 是否删除 1-正常 2-删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建时间 即 加入时间
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
