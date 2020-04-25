package com.mrcoder.framecommon.config.swagger;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger配置类
 * @author: mrcoder
 */
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnProperty("swagger2.basePackage") // 当存在swagger2.basePackage属性配置时，该配置类才生效
public class SwaggerConfig {

    @Value("${swagger2.basePackage}")
    private String basePackage;

    @PostConstruct
    public void init() {
        log.info("SwaggerConfig init......");
    }

    /**
     * 创建API应用 apiInfo() 增加API相关信息
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage(basePackage)) // 为指定包路径下的接口创建API文档
                .paths(PathSelectors.any()) //
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中） 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("API说明文档")//
                .description("API-DOC")//
                .version("1.0.0")//
                .license("Mrcoder").build();
    }

}
