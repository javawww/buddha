package com.buddha.icbi.pojo.news;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 风采展示-数据库实体对象
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
@TableName("news_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class NewsInfo extends PojoModel<NewsInfo> {

    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**
     * 状态 1-审核中 2-通过 3-拒绝
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
	@TableField("cover_img")
	private String coverImg;
    /**
     * 创建人即会员id
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
