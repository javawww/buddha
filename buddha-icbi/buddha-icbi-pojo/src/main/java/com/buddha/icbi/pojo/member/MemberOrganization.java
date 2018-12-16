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
 * 会员所属组织信息（协会OR商会OR母公司）-数据库实体对象
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
@TableName("member_organization")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberOrganization extends PojoModel<MemberOrganization> {

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
     * 名称 某某商会 某某协会 某某母公司
     */
	private String name;
    /**
     * 组织类型 1-商会 2-协会 3-母公司
     */
	private Integer type;
    /**
     * 状态 0-待审核 1-审核通过 2-审核拒绝
     */
	private Integer status;
	/**
	 * 拒绝原因
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
