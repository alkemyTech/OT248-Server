package com.alkemy.ong.swagger;


import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .globalOperationParameters(globalParametersList())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alkemy.ong"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> globalParametersList() {

        val authTokenHeader =
                new ParameterBuilder()
                        .name("Authorization")
                        .modelRef(new ModelRef("string"))
                        .required(false)
                        .parameterType("header")
                        .description("Basic Auth Token")
                        .build();

        return Collections.singletonList(authTokenHeader);

    }


}
