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
 * 会员隐私设置-数据库实体对象
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
@TableName("member_privacy")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberPrivacy extends PojoModel<MemberPrivacy> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 组织id
     */
	@TableField("organization_id")
	private String organizationId;
    /**
     * 组织名称
     */
	@TableField("organization_name")
	private String organizationName;
    /**
     * 是否删除 1-正常 2-删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建人 
     */
	@TableField("create_id")
	private String createId;
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
