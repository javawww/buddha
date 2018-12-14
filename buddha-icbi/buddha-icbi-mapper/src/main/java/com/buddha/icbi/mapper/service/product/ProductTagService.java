package com.buddha.icbi.mapper.service.product;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.icbi.common.param.product.ProductTagParam;
import com.buddha.icbi.mapper.mapper.product.ProductTagMapper;
import com.buddha.icbi.pojo.product.ProductTag;


 /**
 * 
 * 会员产品标签信息 服务实现类
 *
 * #############################################################################
 *
 * CopyRight (C) 2018 ShenZhen FoXiQingNian Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市佛系青年互联网科技有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2018-12-03
 * @版权 深圳市佛系青年互联网科技有限公司(www.fxqn.xin)
 */
@Service
public class ProductTagService extends ServiceImpl<ProductTagMapper, ProductTag> {
	
	/**
	 * 保存产品标签
	 * @param param
	 */
	public void saveTag(ProductTagParam param) {
		//  
		Date curDate = new Date();
		// 删除产品标签
		QueryWrapper<ProductTag> queryWrapper = new QueryWrapper<ProductTag>(new ProductTag());
		queryWrapper.getEntity().setMemberId(param.getMemberId());
		super.remove(queryWrapper);
		// 保存产品标签
		String[] tagNameArr = param.getTagNameArr();
		for (String tagName : tagNameArr) {
			ProductTag tag = new ProductTag();
			tag.setTagName(tagName);
			tag.setMemberId(param.getMemberId());
			tag.setCreateTime(curDate);
			tag.setUpdateTime(curDate);
			super.save(tag);
		}
	}
	
}
