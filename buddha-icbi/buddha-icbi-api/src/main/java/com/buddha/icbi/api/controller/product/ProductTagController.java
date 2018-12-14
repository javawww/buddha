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
import com.buddha.icbi.common.param.product.ProductTagParam;
import com.buddha.icbi.mapper.service.product.ProductTagService;
import com.buddha.icbi.pojo.product.ProductTag;

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
@Api(value = "产品标签controller",tags = {"产品标签接口"})
@RestController
@RequestMapping("product-tag")
@Log4j2
public class ProductTagController extends WebBaseController {

	@Autowired
	private ProductTagService tagService;
	
	/**
	 * 保存产品标签
	 * @param param
	 * @return
	 */
	@PostMapping("save")
	public ResultJson saveTag(@RequestBody ProductTagParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			if(StringUtils.isEmpty(param.getTagNameArr()) || param.getTagNameArr().length == 0) {
				log.info("产品标签为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"产品标签为空");
			}
			// 保存
			tagService.saveTag(param);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
	
	/**
	 * 产品标签列表
	 * @param param
	 * @return
	 */
	@PostMapping("list")
	public ResultJson listTag(@RequestBody ProductTagParam param) {
		try {
			if(StringUtils.isNull(param.getMemberId())) {
				log.info("会员id为空");
				return new ResultJson(ResultStatusEnum.PARAMETER_ERROR,"会员id为空");
			}
			// 列表
			QueryWrapper<ProductTag> queryWrapper = new QueryWrapper<ProductTag>(new ProductTag());
			queryWrapper.getEntity().setMemberId(param.getMemberId());
			List<ProductTag> list = tagService.list(queryWrapper);
			return new ResultJson(ResultStatusEnum.COMMON_SUCCESS, list);
		} catch (Exception e) {
			log.error("系统异常，请检查", e);
			return new ResultJson(e);
		}
	}
}
