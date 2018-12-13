package com.buddha.icbi.pojo.member;

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
 * 会员认证日志记录-数据库实体对象
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
@TableName("member_audit_log")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberAuditLog extends PojoModel<MemberAuditLog> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 会员id
     */
	@TableField("member_id")
	private String memberId;
    /**
     * 审核人id
     */
	@TableField("audit_id")
	private String auditId;
    /**
     * 状态   1-待认证 2-认证通过 3-认证驳回
     */
	@TableField("is_certification")
	private Integer isCertification;
    /**
     * 驳回原因
     */
	private String refuse;
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

}
