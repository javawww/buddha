package com.buddha.icbi.api.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.component.common.bean.ResultJson;
import com.buddha.component.common.enums.ResultStatusEnum;
import com.buddha.component.common.util.StringUtils;
import com.buddha.icbi.api.controller.base.WebBaseController;
import com.buddha.icbi.common.param.product.ProductImageParam;
import com.buddha.icbi.mapper.service.product.ProductImageService;
import com.buddha.icbi.pojo.product.ProductImage;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;

/**
 * //在这里注释类文件的作用等信息
 *
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *佛学禅语：
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *  
 * 
 * @作者   	chuck
 * @时间 	2018年12月4日
 * @版权  	深圳市佛系派互联网科技集团
 */
@CrossOrigin
@Api(value = "产品图片controller",tags = {"产品图片接口"})
@RestController
@RequestMapping("product-image")
@Log4j2
public class ProductImageController extends WebBaseController{

	@Autowired
	private ProductImageService imageService;
	
	/** 
	 * 保存产品图片地址
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveImage(@RequestBody ProductImageParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			if(StringUtils.isEmpty(param.getImgUrlArr()) || param.getImgUrlArr().length == 0) {
				log.info("产品图片为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品图片为空");
			}
			// 保存
			imageService.saveImage(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 获取产品列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson listImage(@RequestBody ProductImageParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 列表
			QueryWrapper<ProductImage> queryWrapper = new QueryWrapper<ProductImage>(new ProductImage());
			queryWrapper.getEntity().setMemberId(param.getMemberId());
			List<ProductImage> images = imageService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, images);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
}
