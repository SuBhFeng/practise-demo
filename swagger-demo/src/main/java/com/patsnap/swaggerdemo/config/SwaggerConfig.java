package com.patsnap.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2   // 开启swagger2的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docker(Environment environment){

        // 设置要显示swagger的环境
        Profiles profiles = Profiles.of("dev","test");
        // 判断当前是否处于该环境
        boolean b = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("sufeng")
                .enable(b)
                .select()
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.patsnap.swaggerdemo.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以/api开头的接口
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("sufeng","https://www.jianshu.com/u/9bca4489b5d6","1529155201@qq.com");
        ApiInfo apiInfo = new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接",// 许可连接
                new ArrayList<>() // 扩展
        );
        return apiInfo;
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group1");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group2");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("group3");
    }

    @Bean
    public Docket docket4(){
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
