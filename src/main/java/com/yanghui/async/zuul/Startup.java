package com.yanghui.async.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.ZuulProxyAutoConfiguration;

@EnableZuulProxy
@SpringBootApplication(exclude=ZuulProxyAutoConfiguration.class)
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
