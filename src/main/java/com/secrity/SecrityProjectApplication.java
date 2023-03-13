package com.secrity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.secrity.mapper")
public class SecrityProjectApplication {

	public static void main(String[] args) {
	    //返回spring容器，断点查看容器内的过滤器
		ConfigurableApplicationContext run = SpringApplication.run(SecrityProjectApplication.class, args);
	
	}

}
