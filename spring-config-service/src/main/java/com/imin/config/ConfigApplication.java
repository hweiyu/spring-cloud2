package com.imin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
*
* @Title: code
* @Description: code
* @author code
* @date 2018年2月28日 下午4:47:55
* @version V1.0  
* 
**/
@EnableConfigServer
@SpringBootConfiguration
@EnableAutoConfiguration
public class ConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
