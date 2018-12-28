package com.buddha.icbi.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buddha.icbi.common.dto.MessageInfoDto;
import com.buddha.icbi.common.param.message.MessageInfoParam;
import com.buddha.icbi.mapper.service.activity.ActivityInfoTplService;
import com.buddha.icbi.mapper.service.company.CompanyInfoTplService;
import com.buddha.icbi.mapper.service.member.MemberInfoService;
import com.buddha.icbi.mapper.service.message.MessageInfoService;
import com.buddha.icbi.pojo.activity.ActivityInfoTpl;
import com.buddha.icbi.pojo.company.CompanyInfoTpl;
import com.buddha.icbi.pojo.message.MessageInfo;


/**
 * Unit test for simple App.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	 
	@Autowired
	private CompanyInfoTplService companyTplService;
	
	@Autowired
	private ActivityInfoTplService activityTplService;
	
	@Autowired
	private MessageInfoService messageService;
	@Test
	public void testMessageList() {
		MessageInfoParam param = new MessageInfoParam();
		param.setMemberId("18acc26d9a2f2672ffda30b74a6d42a1");
		List<MessageInfoDto> dotList = messageService.recentList(param);
		System.out.println(JSON.toJSONString(dotList, true));
	}
	
	@Test
	public void testChatList() {
		MessageInfoParam param = new MessageInfoParam();
		param.setFromId("5585278698f38d5ca9ed9a97d4b1621c");
		param.setToId("18acc26d9a2f2672ffda30b74a6d42a1");
		List<MessageInfo> msgList = messageService.chatList(param);
		System.out.println(JSON.toJSONString(msgList, true));
	}
	// 时间
	Date curdate = new Date();
	/**
	 * 新增一条公司信息模板数据
	 */
	@Test
	public void saveCompanyTpl() {
		CompanyInfoTpl entity = new CompanyInfoTpl();
		entity.setTplName("通用模板");
		entity.setTplCode("1001");
		entity.setLatitude(new BigDecimal(22.72128));
		entity.setLongitude(new BigDecimal(114.06049));
		entity.setName("金记电脑电器冷气维修");
		entity.setAddress("桂澜中路与澜花路交叉口东50米");
		entity.setFloor("5楼507室");
		entity.setRealAvatar("");// 真实头像
		entity.setRealName("张晓明");
		entity.setIdentityFront("");// 身份证
		entity.setIdentityBack("");
		entity.setBusinessCard("");// 名片
		entity.setMobile("13612345678");
		entity.setLandlineNumber("0755-8888888");
		entity.setCompanyProduct("");// 公司产品图片
		entity.setCompanyTag("进口|便宜|量大从优");
		entity.setCompanyProfile("公司简介. 中兴通讯是全球领先的综合通信解决方案提供商。公司成立于1985年，是在香港和深圳两地上市的大型通讯设备公司。");
		entity.setCompanyName("华为技术有限公司");
		entity.setCompanyLicense("");// 营业执照
		entity.setCompanyLogo("");// 公司logo
		entity.setCompanyEnvImg("");// 公司环境照片
		entity.setCompanyWebsite("https://www.huawei.com/cn/");
		entity.setCreateTime(curdate);
		entity.setUpdateTime(curdate);
		
		companyTplService.save(entity);
	}
	
	/**
	 * 更新公司信息模板
	 */
	@Test
	public void updateCompanyTpl() {
		QueryWrapper<CompanyInfoTpl> queryWrapper = new QueryWrapper<CompanyInfoTpl>(new CompanyInfoTpl());
		queryWrapper.getEntity().setTplCode("1001");
		CompanyInfoTpl entity = companyTplService.getOne(queryWrapper);
		entity.setTplName("通用模板");
		entity.setTplCode("1001");
		entity.setLatitude(new BigDecimal(22.72128));
		entity.setLongitude(new BigDecimal(114.06049));
		entity.setName("金记电脑电器冷气维修");
		entity.setAddress("桂澜中路与澜花路交叉口东50米");
		entity.setFloor("5楼507室");
		entity.setRealAvatar("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E7%9C%9F%E5%AE%9E%E5%A4%B4%E5%83%8F.jpg");// 真实头像
		entity.setRealName("张晓明");
		entity.setIdentityFront("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E8%BA%AB%E4%BB%BD%E8%AF%81%E6%AD%A3%E9%9D%A2.jpg");// 身份证
		entity.setIdentityBack("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E8%BA%AB%E4%BB%BD%E8%AF%81%E5%8F%8D%E9%9D%A2.jpg");
		entity.setBusinessCard("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E4%B8%AA%E4%BA%BA%E5%90%8D%E7%89%87.jpg");// 名片
		entity.setMobile("13612345678");
		entity.setLandlineNumber("0755-8888888");
		entity.setCompanyProduct("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E4%BA%A7%E5%93%811.jpg|https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E4%BA%A7%E5%93%812.jpg|https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E4%BA%A7%E5%93%813.jpg");// 公司产品图片
		entity.setCompanyTag("进口|便宜|量大从优");
		entity.setCompanyProfile("公司简介. 中兴通讯是全球领先的综合通信解决方案提供商。公司成立于1985年，是在香港和深圳两地上市的大型通讯设备公司。");
		entity.setCompanyName("华为技术有限公司");
		entity.setCompanyLicense("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E8%90%A5%E4%B8%9A%E6%89%A7%E7%85%A7.jpg");// 营业执照
		entity.setCompanyLogo("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E5%8D%8E%E4%B8%BAlogo.jpg");// 公司logo
		entity.setCompanyEnvImg("https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E7%8E%AF%E5%A2%831.jpg|https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E7%8E%AF%E5%A2%832.jpg|https://www.7utor.cn/%E6%A8%A1%E6%9D%BF%E7%B4%A0%E6%9D%90/%E7%8E%AF%E5%A2%832.jpg");// 公司环境照片
		entity.setCompanyWebsite("https://www.huawei.com/cn/");
		entity.setUpdateTime(curdate);
		
		companyTplService.updateById(entity);
	}
	
	/**
	 * 新增活动模板
	 */
	@Test
	public void saveActivityTpl() {
		ActivityInfoTpl entity = new ActivityInfoTpl();
		entity.setTplName("通用模板");
		entity.setTplCode("1001");
		entity.setTheme("重返校园");
		entity.setContent("把参会的嘉宾变成学校的孩子，让他们把他们的旧校服翻出来，不仅能创造怀旧的氛围，还能帮助人们团结在一起。你可以把员工打扮成老师，用巨大的数字黑板涂鸦墙，还可以添加一些古怪有趣的座位，比如复古学校的写字桌。");
		entity.setAmount(50);
		entity.setCode("1314校园行");
		entity.setLatitude(new BigDecimal(22.72128));
		entity.setLongitude(new BigDecimal(114.06049));
		entity.setAddress("桂澜中路与澜花路交叉口东50米");
		entity.setPost("");
		entity.setCreateTime(curdate);
		entity.setUpdateTime(curdate);
		
		activityTplService.save(entity);
	}
}
