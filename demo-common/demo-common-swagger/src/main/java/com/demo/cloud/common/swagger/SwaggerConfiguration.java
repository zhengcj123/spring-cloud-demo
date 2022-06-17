package com.demo.cloud.common.swagger;

import com.demo.cloud.common.core.constant.AuthConstants;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuYuQi
 * @version 1.0
 * @description: TODO
 * @date 2022/2/28 15:07
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
//        List<Parameter> params = new ArrayList<>();
//        Parameter header = new ParameterBuilder().name(AuthConstants.AUTHORIZATION_KEY).description("用户凭证")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        params.add(header);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(params)
                ;

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo-cloud")
                .description("demo-cloud")
                .termsOfServiceUrl("https://www.baidu.com")
                .version("v1.0.0")
                .build();
    }
}
