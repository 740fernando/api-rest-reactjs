package br.com.nttdata.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String LICENSE_OF_URL = "License of URL";
	private static final String LICENSE_OF_API = "License of API";
	private static final String EMAIL = "your_email@gmail.com";
	private static final String URL = "www.apinttdata.com.br";
	private static final String AUTHOR = "Fernando Luiz";
	private static final String TERMS_OF_SERVICE_URL = "Terms Of Service Url";
	private static final String V1 = "v1";
	private static final String SOME_DESCRIPTION_ABOUT_YOUR_API = "Some description about your API";
	private static final String RESTFUL_API_WITH_SPRING_BOOT = "Restful API with Spring Boot";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.nttdata"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(RESTFUL_API_WITH_SPRING_BOOT, SOME_DESCRIPTION_ABOUT_YOUR_API, V1, TERMS_OF_SERVICE_URL,
				new Contact(AUTHOR,URL,EMAIL), LICENSE_OF_API, LICENSE_OF_URL, Collections.emptyList());
	}
}
