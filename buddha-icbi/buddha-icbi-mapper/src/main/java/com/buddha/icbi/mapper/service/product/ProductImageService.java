package com.buddha.icbi.mapper.service.product;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.icbi.common.param.product.ProductImageParam;
import com.buddha.icbi.mapper.mapper.product.ProductImageMapper;
import com.buddha.icbi.pojo.product.ProductImage;


 /**
 * 
 * 会员产品图片信息 服务实现类
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
public class ProductImageService extends ServiceImpl<ProductImageMapper, ProductImage> {
	
	/**
	 * 保存产品图片
	 * @param param
	 */
	public void saveImage(ProductImageParam param) {
		//  
		Date curDate = new Date();
		// 删除产品图片
		QueryWrapper<ProductImage> queryWrapper = new QueryWrapper<ProductImage>(new ProductImage());
		queryWrapper.getEntity().setMemberId(param.getMemberId());
		super.remove(queryWrapper);
		// 遍历
		String[] imgUrlArr = param.getImgUrlArr();
		for (String imgUrl : imgUrlArr) {
			ProductImage image = new ProductImage();
			image.setImgUrl(imgUrl);
			image.setMemberId(param.getMemberId());
			image.setCreateTime(curDate);
			image.setUpdateTime(curDate);
			super.save(image);
		}
	}
	
}
