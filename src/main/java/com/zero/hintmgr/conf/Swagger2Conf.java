package com.zero.hintmgr.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* URL: http://localhost:8080/hintmgr/swagger-ui.html
*/
@Configuration
@EnableSwagger2
public class Swagger2Conf {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        Parameter p = builder.name("sessionId").required(false).modelRef(new ModelRef("string")).parameterType("header").build();
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(p);
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zero.hintmgr.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(params);
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HintMgr API")
                .description("0.1")
                .termsOfServiceUrl("http://localhost:8080/hintmgr")                
                .version("1.0")
                .license("License")
                .licenseUrl("license.html")
                .build();  //.licenseUrl("dummy/list")
    }
}