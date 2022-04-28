package com.publicis.creditcardprovider.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    public static final Contact contact = new Contact("Venu", "", "venu56gopal@gmail.com");
    public static final ApiInfo API_INFO = new ApiInfo("Credit Card Provider", "Application for Credit Card Provider to Add Credit Card Details And Return All Credit Cards", "1.0",
            "", contact, "", "", Arrays.asList());

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO);
    }

}