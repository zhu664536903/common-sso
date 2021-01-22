package com.gczhu.commonsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.gczhu.commonsso.mapper")
@SpringBootApplication
public class CommonSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSsoApplication.class, args);
	}

}
