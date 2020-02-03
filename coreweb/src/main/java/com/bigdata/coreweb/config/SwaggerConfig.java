package com.bigdata.coreweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bigdata.coreweb"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("cms api")
                .contact(new Contact("wl","http://wl.com",""))
                .version("1.0.0")
                .description("cms api desc")
                .build();
    }
}
