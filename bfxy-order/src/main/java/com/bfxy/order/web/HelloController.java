package com.bfxy.order.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bfxy.store.api.HelloServiceApi;

@RestController
public class HelloController {

	/**
	 * 使用Reference注解，获取服务
	 * dubbo天然支持负载均衡
	 */
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.bfxy.store.service.HelloServiceApi",
            check = false,
            timeout = 1000,
            // 重试的次数。如果下游没有做好冥等的操作，不要做重试。
            retries = 0
    )
	private HelloServiceApi helloService;
    
    @RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		System.out.println("---------HelloController");
		return helloService.sayHello(name);
	}
}
