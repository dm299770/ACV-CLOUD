package com.xxx.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 按照官方的建议放在root目录下，这样才能扫描到Service和dao，不然还会引起，扫描不到注解的问题
 * @author luomouren
 */
@SpringBootApplication
// mapper.java扫描
@MapperScan("com.xxx.demo.mapper")
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//@EnableAsync(proxyTargetClass = true)    //配置代理为cglib代理，默认使用 的是jdk动态代理
@EnableEurekaClient
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 配置上传文件大小的配置
	 * @return
	 */
//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//  单个数据大小
//		factory.setMaxFileSize("102400KB");
//		/// 总上传数据大小
//		factory.setMaxRequestSize("102400KB");
//		return factory.createMultipartConfig();
//
//	}
}