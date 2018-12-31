package com.buddha.icbi.pojo.job;

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
 * -数据库实体对象
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
 * @时间 2018-12-31
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@TableName("job_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class JobInfo extends PojoModel<JobInfo> {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.UUID)
	private String id;
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
	@TableField("job_desc")
	private String jobDesc;
    /**
     * 待遇
     */
	@TableField("salary_desc")
	private String salaryDesc;
    /**
     * 状态 1-审核中 2-通过 3-拒绝
     */
	private Integer status;
    /**
     * 创建人id
     */
	@TableField("create_id")
	private String createId;
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
