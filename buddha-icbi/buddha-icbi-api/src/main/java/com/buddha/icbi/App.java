package com.buddha.icbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * //在这里注释类文件的作用等信息
 *
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *佛学禅语：
 *<br/>卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍卍<br/>
 *  
 * 
 * @作者 悟透弟子
 * @时间 2018年12月13日
 * @版权   深圳市佛系派互联网科技集团
 */
@ComponentScan(basePackages = {
		"com.buddha.component.wechat.config",
		"com.buddha.component.wechat.service",
		"com.buddha.component.redis.config",
		"com.buddha.component.redis.service",
		"com.buddha.icbi.mapper.service",
		"com.buddha.icbi.api.config",
		"com.buddha.icbi.api.controller",
})
@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@RestController
public class App{
    public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}
