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
 * 收藏名片信息-数据库实体对象
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
@TableName("member_collection")
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberCollection extends PojoModel<MemberCollection> {

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
     * 会员真实头像
     */
	@TableField("member_real_avatar")
	private String memberRealAvatar;
    /**
     * 会员真实姓名
     */
	@TableField("member_real_name")
	private String memberRealName;
    /**
     * 是否删除 1-整除 2-删除
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 收藏人id
     */
	@TableField("create_id")
	private String createId;
    /**
     * 收藏人昵称
     */
	@TableField("create_nick_name")
	private String createNickName;
    /**
     * 收藏时间
     */
	@TableField("create_time")
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
