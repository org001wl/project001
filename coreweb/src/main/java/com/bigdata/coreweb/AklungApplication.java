package com.bigdata.coreweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.bigdata.coreweb.mapper")
@SpringBootApplication
@EnableSwagger2
//@EnableTransactionManagement(proxyTargetClass = true)
public class AklungApplication {

	public static void main(String[] args) {
		SpringApplication.run(AklungApplication.class, args);
	}

}
